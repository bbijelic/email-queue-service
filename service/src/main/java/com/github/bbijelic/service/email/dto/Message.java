package com.github.bbijelic.service.email.dto;

import java.util.List;
import java.util.Optional;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Message DTO
 * 
 * @author Bojan Bijelic
 */
public class Message {
    
    /**
     * List of mail 'TO' recepients
     */
    @NotEmpty
    @JsonProperty("to")
    private List<String> to;
    
    /**
     * List of mail 'TO' recepients getter
     * @return the list of mail 'TO' recepients
     */
    public List<String> getTo() {
        return to;
    }
    
    /**
     * List of mail 'TO' recepients
     * @param to the list of mail 'TO' recepients
     */
    public void setTo(List<String> to) {
        this.to = to;
    }
    
    /**
     * List of mail 'CC' recepients
     */
    @JsonProperty("cc")
    private List<String> cc;
    
    /**
     * List of mail 'CC' recepients getter
     * @return the list of mail 'CC' recepients optional
     */
    public Optional<List<String>> getCc() {
        return Optional.ofNullable(cc);
    }
    
    /**
     * List of mail 'CC' recepients setter
     * @param cc the list of mail 'CC' recepients
     */
    public void setCc(List<String> cc) {
        this.cc = cc;
    }
    
    /**
     * List of mail 'BCC' recepients
     */
    @JsonProperty("bcc")
    private List<String> bcc;
    
    /**
     * List of mail 'BCC' recepients getter
     * @return the list of mail 'BCC' recepients optional
     */
    public Optional<List<String>> getBcc() {
        return Optional.ofNullable(bcc);
    }
    
    /**
     * List of mail 'BCC' recepients setter
     * @param bcc the list of mail 'BCC' recepients
     */
    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }
    
    /**
     * Mail subject
     */
    @JsonProperty("subject")
    private String subject;
    
    /**
     * Mail subject getter
     * @return the mail subject
     */
    public String getSubject() {
        return subject;
    }
    
    /**
     * Mail subject setter
     * @param subject the mail subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /**
     * Mail body
     */
    @NotBlank
    @JsonProperty("body")
    private String body;
    
    /**
     * Mail body getter
     * @return the mail body
     */
    public String getBody() {
        return body;
    }
    
    /**
     * Mail body setter
     * @param body the mail body
     */
    public void setBody(String body) {
        this.body = body;
    }
    
    /**
     * Attachments
     */
    @JsonProperty("attachments")
    private List<MessageAttachment> attachments;
    
    /**
     * Attachments getter
     */
    public Optional<List<MessageAttachment>> getAttachments() {
        return Optional.ofNullable(attachments);
    }
    
    /**
     * Attachments setter
     * @param attachments the attachments
     */
    public void setAttachments(List<MessageAttachment> attachments) {
        this.attachments = attachments;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("to", to)
            .add("cc", cc)
            .add("bcc", bcc)
            .add("subject", subject)
            .add("bodySize", body.length())
            .add("attachments", attachments)
            .toString();
    }
}
