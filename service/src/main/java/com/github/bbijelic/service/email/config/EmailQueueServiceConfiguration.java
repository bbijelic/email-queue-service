package com.github.bbijelic.service.email.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.bbijelic.service.email.config.mail.MailerConfiguration;
import com.google.common.base.MoreObjects;

import io.dropwizard.Configuration;

/**
 * Email queue service configuration
 * 
 * @autho Bojan Bijelic
 */
public class EmailQueueServiceConfiguration extends Configuration {
    
    /**
     * Queue configuration
     */
    @Valid
    @NotNull
    @JsonProperty("amqp")
    private QueueConfiguration queueConfiguration;
    
    /**
     * Queue configuration getter
     * @return the queue configuration
     */
    public QueueConfiguration getQueueConfiguration() {
        return queueConfiguration;
    }
    
    /**
     * Queue configuration setter
     * @param queueConfiguration the queue configuration
     */
    public void setQueueConfiguration(QueueConfiguration queueConfiguration) {
        this.queueConfiguration = queueConfiguration;
    }
    
    /**
     * Mailer configuration
     */
    @Valid
    @NotNull
    @JsonProperty("mail")
    private MailerConfiguration mailerConfiguration;
    
    /**
     * Mailer configuration getter
     * @return the mailer configuration
     */
    public MailerConfiguration getMailerConfiguration() {
        return mailerConfiguration;
    }
    
    /**
     * Mailer configuration setter
     * @param mailerConfiguration the mailer configuration
     */
    public void setMailerConfiguration(MailerConfiguration mailerConfiguration) {
        this.mailerConfiguration = mailerConfiguration;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("queueConfiguration", queueConfiguration)
            .add("mailerConfiguration", mailerConfiguration)
            .toString();
    }
}
