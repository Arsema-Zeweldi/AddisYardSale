package org.web2.addisyardsale.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="messages")
public class Message {
    @Id
    private Long MessageId;
    private String MessageContent;
    private LocalDateTime Timestamp;
    private Long SenderId;
    private Long ReceiverId;

    public Long getMessageId() {
        return MessageId;
    }

    public void setMessageId(Long messageId) {
        MessageId = messageId;
    }

    public String getMessageContent() {
        return MessageContent;
    }

    public void setMessageContent(String messageContent) {
        MessageContent = messageContent;
    }

    public LocalDateTime getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(LocalDateTime now) {
        Timestamp = now;
    }

    public Long getSenderId() {
        return SenderId;
    }

    public void setSenderId(Long senderId) {
        SenderId = senderId;
    }

    public Long getReceiverId() {
        return ReceiverId;
    }

    public void setReceiverId(Long receiverId) {
        ReceiverId = receiverId;
    }
}
