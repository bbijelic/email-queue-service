package com.github.bbijelic.service.email.config.mail;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import io.dropwizard.Configuration;

/**
 * SMTP configuration
 * 
 * @author Bojan Bijelic
 */
public class SmtpConfiguration extends Configuration {
    
    /**
     * SMTP hostname
     */
    @NotNull
    @NotEmpty
    @JsonProperty("host")
    private String host;
    
    /**
     * Host getter
     * @return the smtp host server
     */
    public String getHost() {
        return host;
    }
    
    /**
     * Host setter
     * @param host the host
     */
    public void setHost(String host) {
        this.host = host;
    }
    
    /**
     * Port
     */
    @Min(1)
    @Max(65535)
    @JsonProperty("port")
    private int port;
    
    /**
     * Port getter
     * @return the port
     */
    public int getPort() {
        return port;
    }
    
    /**
     * Port setter
     * @param port the port
     */
    public void setPort(int port) {
        this.port = port;
    }
    
    /**
     * Username
     */
    @NotEmpty
    @JsonProperty("username")
    private String username;
    
    /**
     * Username getter
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Username setter
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Password
     */
    @NotEmpty
    @JsonProperty("password")
    private String password;
    
    /**
     * Password getter
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Password setter
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Transport
     */
    @JsonProperty("transport")
    private Transport transport = Transport.PLAIN;
    
    /**
     * Transport getter
     * @return the transport
     */
    public Transport getTransport() {
        return transport;
    }
    
    /**
     * Transport setter
     * @param transport the transport
     */
    public void setTransport(Transport transport) {
        this.transport = transport;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("host", host)
            .add("port", port)
            .add("username", username)
            .add("password", "***")
            .add("transport", transport)
            .toString();
    }
}
