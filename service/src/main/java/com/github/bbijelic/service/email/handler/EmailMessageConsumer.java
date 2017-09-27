package com.github.bbijelic.service.email.handler;

import java.io.IOException;

import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bbijelic.service.email.dto.Message;
import com.github.bbijelic.service.email.dto.MessageAttachment;
import com.github.bbijelic.service.email.dto.MessageRecipient;
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
                        
            // Get an instance of an email
            Email email = new Email();
            
            // Get the 'FROM' recipient
            email.setFromAddress(message.getFrom().getName(), message.getFrom().getAddress());
            
            // Set 'TO' recipients
            for(MessageRecipient toRecipient : message.getTo()){
                email.addNamedToRecipients(toRecipient.getName(), toRecipient.getAddress());
            }
            
            // If CC list is set
            if(message.getCc().isPresent()){
                // Set 'CC' recipients
                for(MessageRecipient ccRecipient : message.getCc().get()){
                    email.addNamedCcRecipients(ccRecipient.getName(), ccRecipient.getAddress());
                }                
            }
            
            // If CC list is set
            if(message.getBcc().isPresent()){
                // Set 'BCC' recipients
                for(MessageRecipient bccRecipient : message.getBcc().get()){
                    email.addNamedBccRecipients(bccRecipient.getName(), bccRecipient.getAddress());
                }     
            }
            
            // Set subject
            email.setSubject(message.getSubject());
            
            // Set text
            email.setText(message.getBody());
            
            // Check if there are attachments
            if(message.getAttachments().isPresent()){
                for(MessageAttachment messageAttachment : message.getAttachments().get()){
                    email.addAttachment(
                        messageAttachment.getFilename(), 
                        messageAttachment.getContent(), 
                        messageAttachment.getContentType());   
                }
            }

            // Send an email
            mailer.sendMail(email);
            
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
