package com.github.bbijelic.service.email.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Message attachment
 * 
 * @author Bojan Bijelic
 */
public class MessageAttachment {
    
    /**
     * Attachment filename
     */
    @NotBlank
    @JsonProperty("filename")
    private String filename;
    
    /**
     * Attachment filename getter
     * @return the attachment filename
     */
    public String getFilename() {
        return filename;
    }
    
    /**
     * Attachment filename setter
     * @param filename the attachment filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }    
    
    /**
     * Content type
     */
    @NotBlank
    @JsonProperty("contentType")
    private String contentType;
    
    /**
     * Content type getter
     * @return the content type
     */
    public String getContentType() {
        return contentType;
    }
    
    /**
     * Content type setter
     * @param contentType the content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    /**
     * Content
     */
    @NotNull
    @JsonProperty("content")
    private byte[] content;
    
    /**
     * Content getter
     * @return the content bytes
     */
    public byte[] getContent() {
        return content;
    }
    
    /**
     * Content setter
     * @param content the content bytes
     */
    public void setContent(byte[] content) {
        this.content = content;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("filename", filename)
            .add("getContentType", contentType)
            .add("contentSize", content.length)
            .toString();
    }
}
