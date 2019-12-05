package com.bdx.message.entity.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author: 磊大大
 * @date: 2019/11/29 14:31
 */
@Entity
@Table(name = "message_reply", schema = "bdx")
public class MessageReply {
    private int id;
    private String messageReplyId;
    private String messageId;
    private String userId;
    private String messageReplyText;
    private Timestamp createTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message_reply_id")
    public String getMessageReplyId() {
        return messageReplyId;
    }

    public void setMessageReplyId(String messageReplyId) {
        this.messageReplyId = messageReplyId;
    }

    @Basic
    @Column(name = "message_id")
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "message_reply_text")
    public String getMessageReplyText() {
        return messageReplyText;
    }

    public void setMessageReplyText(String messageReplyText) {
        this.messageReplyText = messageReplyText;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageReply that = (MessageReply) o;
        return id == that.id &&
                Objects.equals(messageReplyId, that.messageReplyId) &&
                Objects.equals(messageId, that.messageId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(messageReplyText, that.messageReplyText) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageReplyId, messageId, userId, messageReplyText, createTime);
    }
}
