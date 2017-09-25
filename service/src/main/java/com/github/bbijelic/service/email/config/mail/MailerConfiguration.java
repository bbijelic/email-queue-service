package com.github.bbijelic.service.email.config.mail;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import io.dropwizard.Configuration;

/**
 * Mailer configuration
 * 
 * @author Bojan Bijelic
 */
public class MailerConfiguration extends Configuration {
    
    /**
     * SMTP configuration
     */
    @NotNull
    @JsonProperty("smtp")
    private SmtpConfiguration smtpConfiguration;
    
    /**
     * SMTP configruation getter
     * @return the SMTP configuration
     */
    public SmtpConfiguration getSmtpConfiguration() {
        return smtpConfiguration;
    }
    
    /**
     * SMTP configuration setter
     * @param smtpConfiguration the SMTP configuration
     */
    public void setSmtpConfiguration(SmtpConfiguration smtpConfiguration) {
        this.smtpConfiguration = smtpConfiguration;
    }
        
    /**
     * Proxy configuration
     */
    @JsonProperty("proxy")
    private ProxyConfiguration proxyConfiguration;
    
    /**
     * Proxy configuration getter
     * @return the proxy configuration
     */
    public Optional<ProxyConfiguration> getProxyConfiguration() {
        return Optional.ofNullable(proxyConfiguration);
    }
    
    /**
     * Proxy configuration setter
     * @param proxyConfiguration the proxy configuration
     */
    public void setProxyConfiguration(ProxyConfiguration proxyConfiguration) {
        this.proxyConfiguration = proxyConfiguration;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("smtpConfiguration", smtpConfiguration)
            .add("proxyConfiguration", proxyConfiguration)
            .toString();
    }
}
