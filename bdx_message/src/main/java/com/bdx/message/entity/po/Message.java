package com.bdx.message.entity.po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author: 磊大大
 * @date: 2019/12/2 9:49
 */
@Entity
public class Message {
    private int id;
    private String messageId;
    private String userId;
    private String messageTitle;
    private String messageContext;
    private Integer messageNum;
    private Integer messageGood;
    private Byte messageLevel;
    private Date createTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "message_title")
    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    @Basic
    @Column(name = "message_context")
    public String getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
    }

    @Basic
    @Column(name = "message_num")
    public Integer getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(Integer messageNum) {
        this.messageNum = messageNum;
    }

    @Basic
    @Column(name = "message_good")
    public Integer getMessageGood() {
        return messageGood;
    }

    public void setMessageGood(Integer messageGood) {
        this.messageGood = messageGood;
    }

    @Basic
    @Column(name = "message_level")
    public Byte getMessageLevel() {
        return messageLevel;
    }

    public void setMessageLevel(Byte messageLevel) {
        this.messageLevel = messageLevel;
    }


    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                Objects.equals(messageId, message.messageId) &&
                Objects.equals(userId, message.userId) &&
                Objects.equals(messageTitle, message.messageTitle) &&
                Objects.equals(messageContext, message.messageContext) &&
                Objects.equals(messageNum, message.messageNum) &&
                Objects.equals(messageGood, message.messageGood) &&
                Objects.equals(messageLevel, message.messageLevel) &&
                Objects.equals(createTime, message.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageId, userId, messageTitle, messageContext, messageNum, messageGood, messageLevel, createTime);
    }
}
