package com.github.bbijelic.service.email.handler;

import java.io.IOException;

import org.simplejavamail.mailer.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bbijelic.service.email.dto.Message;
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
     * Object mapper
     */
    private ObjectMapper objectMapper;
    
    /**
     * Constructor channel
     * @param channel the amqp channel
     * @param mailer the mailer
     * @param objectMapper the object maper
     */
    public EmailMessageConsumer(
        final Channel channel, 
        final Mailer mailer,
        final ObjectMapper objectMapper) {
            
        super(channel);
        
        this.channel = channel;
        this.mailer = mailer;
        this.objectMapper = objectMapper;
    }
    
    @Override
    public void handleDelivery(
        String consumerTag, 
        Envelope envelope, 
        BasicProperties properties, 
        byte[] body) 
            throws IOException {
            
        LOGGER.debug("Handling message of size: {}", body.length);
        
        try {
        
            // Deserialize message
            Message message = objectMapper.readValue(body, Message.class);
            LOGGER.debug("Message: {}", message.toString());
            
            // Acknowledge the message
            channel.basicAck(envelope.getDeliveryTag(), false);
        
        } catch (JsonParseException jpe){
            LOGGER.info("Could not deserialize inbound message [deliveryTag: {}], rejecting message", 
                envelope.getDeliveryTag());
            
            // Not-acknowledge message
            // Single, do not requeue
            channel.basicNack(envelope.getDeliveryTag(), false, false);
        }
                
    }
    
}
