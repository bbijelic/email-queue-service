package com.github.bbijelic.service.email.config;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("queueConfiguration", queueConfiguration)
            .toString();
    }
}
