package com.github.bbijelic.service.email.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Email message recipient
 * 
 * @author Bojan Bijelic
 */
public class MessageRecipient {
    
    /**
     * Name of the recipient
     */
    @NotEmpty
    @JsonProperty("name")
    private String name;
    
    /**
     * Name getter
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Recipient setter
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }    
    
    /**
     * Address
     */
    @NotEmpty
    @JsonProperty("address")
    private String address;
    
    /**
     * Address getter
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Address setter
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("name", name)
            .add("address", address)
            .toString();
    }
}
