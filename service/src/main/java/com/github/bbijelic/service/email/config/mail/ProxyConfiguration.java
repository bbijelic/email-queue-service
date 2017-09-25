package com.github.bbijelic.service.email.config.mail;

import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import io.dropwizard.Configuration;

/**
 * Proxy configuration
 * 
 * @author Bojan Bijelic
 */
public class ProxyConfiguration extends Configuration {
    
    /**
     * Proxy host
     */
    @NotNull
    @NotEmpty
    @JsonProperty("remoteProxyHost")
    private String remoteProxyHost;
    
    /**
     * Remote proxy host getter
     * @return the remote proxy host
     */
    public String getRemoteProxyHost() {
        return remoteProxyHost;
    }
    
    /**
     * Remote proxy host setter
     * @param remoteProxyHost the remote proxy host
     */
    public void setRemoteProxyHost(String remoteProxyHost) {
        this.remoteProxyHost = remoteProxyHost;
    }
    
    /**
     * Remote proxy port
     */
    @Min(1)
    @Max(65535)
    @JsonProperty("remoteProxyPort")
    private int remoteProxyPort;
    
    /**
     * Remote proxy port getter
     * @return the remote proxy port
     */
    public int getRemoteProxyPort() {
        return remoteProxyPort;
    }
    
    /**
     * Remote proxy port setter
     * @param remoteProxyPort the remote proxy port
     */
    public void setRemoteProxyPort(int remoteProxyPort) {
        this.remoteProxyPort = remoteProxyPort;
    }
    
    /**
     * Username
     */
    @JsonProperty("username")
    private String username;
    
    /**
     * Username getter
     * @return the username
     */
    public Optional<String> getUsername() {
        return Optional.ofNullable(username);
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
    @JsonProperty("password")
    private String password;
    
    /**
     * Password getter
     * @return the password
     */
    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }
    
    /**
     * Password setter
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("remoteProxyHost", remoteProxyHost)
            .add("remoteProxyPort", remoteProxyPort)
            .add("username", username)
            .add("password", "***")
            .toString();
    }
    
}
