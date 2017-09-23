package com.github.bbijelic.service.email.amqp;

import io.dropwizard.lifecycle.Managed;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.service.email.config.QueueConfiguration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * AMQP Handler
 * 
 * @author Bojan Bijelic
 */
public class AmqpHandler implements Managed {
    
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpHandler.class);
    
    /**
     * Queue configuration
     */
    private QueueConfiguration queueConfiguration;
    
    /**
     * AMQP connection factory
     */
    private ConnectionFactory connectionFactory;
    
    /**
     * Constructor
     * 
     * @param queueConfiguration the queue configuration
     */
    public AmqpHandler(final QueueConfiguration queueConfiguration) {
        this.queueConfiguration = queueConfiguration;
        
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
        channel.basicConsume(queue, false, new EmailMessageConsumer(channel));
        
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
