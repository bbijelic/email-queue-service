package com.github.bbijelic.service.email.handler;

import io.dropwizard.lifecycle.Managed;

import java.util.UUID;

import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ProxyConfig;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bbijelic.service.email.config.QueueConfiguration;
import com.github.bbijelic.service.email.config.mail.MailerConfiguration;
import com.github.bbijelic.service.email.config.mail.ProxyConfiguration;
import com.github.bbijelic.service.email.config.mail.Transport;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Inbound Message Handler
 * 
 * @author Bojan Bijelic
 */
public class InboundMessageHandler implements Managed {
    
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InboundMessageHandler.class);
    
    /**
     * Queue configuration
     */
    private QueueConfiguration queueConfiguration;
    
    /**
     * Mailer configuration
     */
    private MailerConfiguration mailerConfiguration;
    
    /**
     * Object mapper
     */
    private ObjectMapper objectMapper;
    
    /**
     * Constructor
     * 
     * @param queueConfiguration the queue configuration
     * @param mailerConfiguration the mailer configuration
     * @param objectMapper the object mapper
     */
    public InboundMessageHandler(
        final QueueConfiguration queueConfiguration,
        final MailerConfiguration mailerConfiguration,
        final ObjectMapper objectMapper) {
            
        this.queueConfiguration = queueConfiguration;
        this.mailerConfiguration = mailerConfiguration;
        this.objectMapper = objectMapper;
        
        initializeMailer();
        initializeAmqp();
    }
    
    /**
     * Mailer
     */
    private Mailer mailer;
    
    /**
     * Initializes Mailer
     */
    private void initializeMailer(){
        
        // Mailer server configuration
        ServerConfig serverConfig = new ServerConfig(
            mailerConfiguration.getSmtpConfiguration().getHost(),
            mailerConfiguration.getSmtpConfiguration().getPort(),
            mailerConfiguration.getSmtpConfiguration().getUsername(),
            mailerConfiguration.getSmtpConfiguration().getPassword());
            
        // Server transport strategy
        TransportStrategy transportStrategy = TransportStrategy.SMTP_PLAIN;
        if(mailerConfiguration.getSmtpConfiguration().getTransport().equals(Transport.SSL)){
            transportStrategy = TransportStrategy.SMTP_SSL;
        } else if(mailerConfiguration.getSmtpConfiguration().getTransport().equals(Transport.TLS)){
            transportStrategy = TransportStrategy.SMTP_TLS;
        }
        
        // Proxy configuration
        ProxyConfig mailerProxyConfig = null;
        if(mailerConfiguration.getProxyConfiguration().isPresent()){
            ProxyConfiguration proxyConfiguration = mailerConfiguration.getProxyConfiguration().get();
            
            if(proxyConfiguration.getUsername().isPresent() 
                && proxyConfiguration.getPassword().isPresent()){
                
                // Proxy config with credentials
                mailerProxyConfig = new ProxyConfig(
                    proxyConfiguration.getRemoteProxyHost(),
                    proxyConfiguration.getRemoteProxyPort(),
                    proxyConfiguration.getUsername().get(),
                    proxyConfiguration.getPassword().get()
                );
                
            } else {
                
                // Simple proxy config without credentials
                mailerProxyConfig = new ProxyConfig(
                    proxyConfiguration.getRemoteProxyHost(),
                    proxyConfiguration.getRemoteProxyPort());
            }
        }
        
        // Initialize mailer
        mailer = new Mailer(serverConfig, transportStrategy, mailerProxyConfig);
    }
    
    /**
     * AMQP connection factory
     */
    private ConnectionFactory connectionFactory;
    
    /**
     * Initializes AMQP
     */
    private void initializeAmqp(){
        this.connectionFactory = new ConnectionFactory();
        this.connectionFactory.setHost(queueConfiguration.getHostname());
        this.connectionFactory.setPort(queueConfiguration.getPort());

        if(queueConfiguration.getVirtualHost().isPresent()){
            this.connectionFactory.setVirtualHost(queueConfiguration.getVirtualHost().get());
        }
        
        this.connectionFactory.setUsername(queueConfiguration.getUsername());
        this.connectionFactory.setPassword(queueConfiguration.getPassword());
        
        this.connectionFactory.setTopologyRecoveryEnabled(true);
        this.connectionFactory.setAutomaticRecoveryEnabled(true);
    }
    
    /**
     * AMQP Connection
     */
    private Connection connection;
    
    /**
     * Channel
     */
    private Channel channel;
    
    @Override
    public void start() throws Exception {
        
        // Connect and obtain channel
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
        
        String queueName = "email-queue-" + UUID.randomUUID().toString();
        if(queueConfiguration.getQueue().isPresent()) queueName = queueConfiguration.getQueue().get();
        
        // Declare queue
        String queue = channel.queueDeclare(queueName, true, true, true, null).getQueue();
        
        // Bind queue and exchange with a routing keys
        for(String routingKey : queueConfiguration.getRoutingKeys()){
            channel.queueBind(queueName, queueConfiguration.getExchange(), routingKey);
        }
        
        // Define consumer
        channel.basicConsume(queue, false, new EmailMessageConsumer(channel, mailer, objectMapper));
        
        LOGGER.debug("AMQP handler started");
    }
    
    @Override
    public void stop() throws Exception {
        if (channel != null) {
            channel.close();
        }
        if (connection != null) {
            connection.close();
        }
        
        LOGGER.debug("AMQP handler stopped");
    }
    
}
