package com.github.bbijelic.service.email.config;

import io.dropwizard.Configuration;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Queue configuration
 * 
 * @author Bojan Bijelic
 */
public class QueueConfiguration extends Configuration {
    
    /**
     * Server hostname
     */
    @NotEmpty
    @JsonProperty("hostname")
    private String hostname;
    
    /**
     * Hostname getter
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }
    
    /**
     * Hostname setter
     * @param hostname the hostname
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
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
     * Virtual host
     */
    @JsonProperty("virtualHost")
    private String virtualHost;
    
    /**
     * Virtual host getter
     * @return the virtual host
     */
    public Optional<String> getVirtualHost() {
        return Optional.ofNullable(virtualHost);
    }
    
    /**
     * Virtual host setter
     * @param virtualHost the virtual host
     */
    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
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
     * Exchange
     */
    @NotEmpty
    @JsonProperty("exchange")
    private String exchange;
    
    /**
     * Exchange getter
     * @return the exchange
     */
    public String getExchange() {
        return exchange;
    }
    
    /**
     * Exchange setter
     * @param exchange the exchange
     */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    
    /**
     * Queue name
     */
    @JsonProperty("queue")
    private String queue;
    
    /**
     * Queue getter
     * @return the queue
     */
    public Optional<String> getQueue() {
        return Optional.ofNullable(queue);
    }
    
    /**
     * Queue setter
     * @param queue the queue
     */
    public void setQueue(String queue) {
        this.queue = queue;
    }
    
    /**
     * Routing keys
     */
    @NotEmpty
    @JsonProperty("routingKeys")
    private List<String> routingKeys;
    
    /**
     * Routing keys getter
     * @return the list of routing keys
     */
    public List<String> getRoutingKeys() {
        return routingKeys;
    }
    
    /**
     * Routing keys setter
     * @param the list of routing keys
     */
    public void setRoutingKeys(List<String> routingKeys) {
        this.routingKeys = routingKeys;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("hostname", hostname)
            .add("port", port)
            .add("virtualHost", virtualHost)
            .add("username", username)
            .add("password", "***")
            .add("exchange", exchange)
            .add("queue", queue)
            .add("routingKeys", routingKeys)
            .toString();
    }
}
