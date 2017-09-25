package com.github.bbijelic.service.email;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.service.email.config.EmailQueueServiceConfiguration;
import com.github.bbijelic.service.email.handler.InboundMessageHandler;

/**
 * Email Queue Service Main Class
 * 
 * @author Bojan Bijelic
 */
public class EmailQueueService extends Application<EmailQueueServiceConfiguration> {
    
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailQueueService.class);
    
    /**
     * Service instance uuid
     */
    private UUID serviceInstanceUUID;
    
    /**
     * Constructor
     */
    public EmailQueueService() {
        serviceInstanceUUID = UUID.randomUUID();
    }
    
    @Override
    public String getName() {
        return "email-queue-service-" + serviceInstanceUUID;
    }
    
    /**
     * Main method
     */
    public static void main(String[] args) throws Exception {
        new EmailQueueService().run(args);
    }
    
    @Override
    public void initialize(Bootstrap<EmailQueueServiceConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
            new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false)));
    }

    @Override
    public void run(EmailQueueServiceConfiguration config, Environment env) throws Exception {
        LOGGER.info("Starting " + getName());
        
        // Manage Amqp Handler
        env.lifecycle().manage(
            new InboundMessageHandler(
                config.getQueueConfiguration(),
                config.getMailerConfiguration()));
    }
    
}
