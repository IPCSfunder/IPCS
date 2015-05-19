package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Chen Chao
 */

@Entity
@Table(name = "MESSAGE")
public class Message extends BasicObject {
    private Long objectId;

    private String header;

    private String content;

    private String attachmentAddress;

    private MessageType messageType;

    private Person fromUser;

    public Message() {
    }

    public Message(MessageBuilder messageBuilder){
        this.header = messageBuilder.header;
        this.content = messageBuilder.content;
        this.attachmentAddress = messageBuilder.attachmentAddress;
        this.messageType = messageBuilder.messageType;
        this.fromUser = messageBuilder.fromUser;
    }


    public static class MessageBuilder{
        private String header;
        private String content;
        private String attachmentAddress;
        private MessageType messageType;
        private Person fromUser;

        public MessageBuilder withHeader(String header){
            this.header = header;
            return this;
        }

        public MessageBuilder withContent(String content){
            this.content = content;
            return this;
        }

        public MessageBuilder withAttachmentAddress(String attachmentAddress){
            this.attachmentAddress = attachmentAddress;
            return this;
        }

        public MessageBuilder withMessageType(MessageType messageType){
            this.messageType = messageType;
            return this;
        }

        public MessageBuilder withFromUser(Person fromUser){
            this.fromUser = fromUser;
            return this;
        }

        public Message builder(){
            return new Message(this);
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_OBJID", unique = true, nullable = false)
    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    @Column
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }


    @Column(name="ATTACHMENT_ADDRESS")
    public String getAttachmentAddress() {
        return attachmentAddress;
    }

    public void setAttachmentAddress(String attachmentAddress) {
        this.attachmentAddress = attachmentAddress;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MESSAGE_TYPE_FK")
    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FROM_USER")
    public Person getFromUser() {
        return fromUser;
    }

    public void setFromUser(Person fromUser) {
        this.fromUser = fromUser;
    }

    public int hashCode() {
        int factor = 31;
        int result = 17 * factor + header.hashCode();
        result = 17 * result + content.hashCode();
        return result;
    }

    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (this == obj)
            return true;
        if (obj.getClass() != Permission.class)
            return false;
        Message message = (Message) obj;
        return this.header.equals(message.getHeader()) && this.content.equals(message.getContent());

    }

    public String toString() {
        return "Message header is " + header + " and content is " + content + super.toString();
    }


}
