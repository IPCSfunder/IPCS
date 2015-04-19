package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import java.util.Date;

/**
 * @author Chen Chao
 */
public class Message extends BasicObject {

    private String header;

    private String content;

    private String attachmentAddress;

    private Date sentTime;

    private MessageType messageType;

    private Person fromUser;

    private Person toUser;

    public Message() {
    }

    public Message(MessageBuilder messageBuilder){
        this.header = messageBuilder.header;
        this.content = messageBuilder.content;
        this.attachmentAddress = messageBuilder.attachmentAddress;
        this.messageType = messageBuilder.messageType;
        this.fromUser = messageBuilder.fromUser;
        this.toUser = messageBuilder.toUser;
    }


    public static class MessageBuilder{
        private String header;
        private String content;
        private String attachmentAddress;
        private Date sentTime;
        private MessageType messageType;
        private Person fromUser;
        private Person toUser;

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

        public MessageBuilder withDate(Date sentTime){
            this.sentTime= sentTime;
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

        public MessageBuilder withToUser(Person toUser){
            this.toUser = toUser;
            return this;
        }

        public Message builder(){
            return new Message(this);
        }

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public String getAttachmentAddress() {
        return attachmentAddress;
    }

    public void setAttachmentAddress(String attachmentAddress) {
        this.attachmentAddress = attachmentAddress;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Person getToUser() {
        return toUser;
    }

    public void setToUser(Person toUser) {
        this.toUser = toUser;
    }

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
        result = 17 * result + sentTime.hashCode();
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
        return this.header.equals(message.getHeader()) && this.content.equals(message.getContent()) && this.equals((message.getSentTime()));

    }

    public String toString() {
        return "Message sent time is " + sentTime + "and  header is " + header + " and content is " + content + super.toString();
    }


}
