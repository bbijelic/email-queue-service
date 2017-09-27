package com.github.bbijelic.service.email.dto;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
     * Email author
     */
    @Valid
    @NotNull
    @JsonProperty("from")
    private MessageRecipient from;
    
    /**
     * From getter
     * @return the 'FROM' address
     */
    public MessageRecipient getFrom() {
        return from;
    }
    
    /**
     * From setter
     * @param from the 'from' addr
     */
    public void setFrom(MessageRecipient from) {
        this.from = from;
    }
    
    /**
     * List of mail 'TO' recipients
     */
    @NotEmpty
    @JsonProperty("to")
    private List<MessageRecipient> to;
    
    /**
     * List of mail 'TO' recipients getter
     * @return the list of mail 'TO' recipients
     */
    public List<MessageRecipient> getTo() {
        return to;
    }
    
    /**
     * List of mail 'TO' recepients
     * @param to the list of mail 'TO' recipients
     */
    public void setTo(List<MessageRecipient> to) {
        this.to = to;
    }
    
    /**
     * List of mail 'CC' recipients
     */
    @JsonProperty("cc")
    private List<MessageRecipient> cc;
    
    /**
     * List of mail 'CC' recipients getter
     * @return the list of mail 'CC' recipients optional
     */
    public Optional<List<MessageRecipient>> getCc() {
        return Optional.ofNullable(cc);
    }
    
    /**
     * List of mail 'CC' recipients setter
     * @param cc the list of mail 'CC' recipients
     */
    public void setCc(List<MessageRecipient> cc) {
        this.cc = cc;
    }
    
    /**
     * List of mail 'BCC' recipients
     */
    @JsonProperty("bcc")
    private List<MessageRecipient> bcc;
    
    /**
     * List of mail 'BCC' recipients getter
     * @return the list of mail 'BCC' recipients optional
     */
    public Optional<List<MessageRecipient>> getBcc() {
        return Optional.ofNullable(bcc);
    }
    
    /**
     * List of mail 'BCC' recipients setter
     * @param bcc the list of mail 'BCC' recipients
     */
    public void setBcc(List<MessageRecipient> bcc) {
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
            .add("from", from)
            .add("to", to)
            .add("cc", cc)
            .add("bcc", bcc)
            .add("subject", subject)
            .add("bodySize", body.length())
            .add("attachments", attachments)
            .toString();
    }
}
