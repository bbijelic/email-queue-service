package com.github.bbijelic.service.email.handler;

import java.io.IOException;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * Email message consumer
 * 
 * @author Bojan Bijelic
 */
public class EmailMessageConsumer extends DefaultConsumer {
    
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailMessageConsumer.class);  
    
    /**
     * AMQP channel
     */
    private Channel channel;
    
    /**
     * Mailer
     */
    private Mailer mailer;
    
    /**
     * Constructor channel
     * @param channel the amqp channel
     */
    public EmailMessageConsumer(final Channel channel, final Mailer mailer) {
        super(channel);
        
        this.channel = channel;
        this.mailer = mailer;
    }
    
    @Override
    public void handleDelivery(
        String consumerTag, 
        Envelope envelope, 
        BasicProperties properties, 
        byte[] body) 
            throws IOException {
            
        LOGGER.debug("Handling message of size: {}", body.length);
        
        // TODO Handle inbound message
        
        // Acknowledge the message
        channel.basicAck(envelope.getDeliveryTag(), false);
    }
    
}
