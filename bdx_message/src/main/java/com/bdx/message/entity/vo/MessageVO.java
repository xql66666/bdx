package com.bdx.message.entity.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: 磊大大
 * @date: 2019/12/2 14:53
 */
//@Entity
public class MessageVO implements Serializable {


    @Id
    @Column(name = "nickname")
    private String nickname;

    private String messageId;

    @Column(name = "message_title")
    private String messageTitle;
    @Column(name = "message_context")
    private String messageContext;
    @Column(name = "message_good")
    private Integer messageGood;
    @Column(name = "message_num")
    private Integer messageNum;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "message_level")
    private Byte messageLevel;
    private List<ReplyVO> reply;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
    }

    public Integer getMessageGood() {
        return messageGood;
    }

    public void setMessageGood(Integer messageGood) {
        this.messageGood = messageGood;
    }

    public Integer getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(Integer messageNum) {
        this.messageNum = messageNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getMessageLevel() {
        return messageLevel;
    }

    public void setMessageLevel(Byte messageLevel) {
        this.messageLevel = messageLevel;
    }

    public List<ReplyVO> getReply() {
        return reply;
    }

    public void setReply(List<ReplyVO> reply) {
        this.reply = reply;
    }

    public MessageVO(String nickname, String messageId, String messageTitle, String messageContext, Integer messageGood, Integer messageNum, Date createTime, Byte messageLevel) {
        this.nickname = nickname;
        this.messageId = messageId;
        this.messageTitle = messageTitle;
        this.messageContext = messageContext;
        this.messageGood = messageGood;
        this.messageNum = messageNum;
        this.createTime = createTime;
        this.messageLevel = messageLevel;
    }

    public MessageVO() {
    }

    public MessageVO(String nickname, String messageId, String messageTitle, String messageContext, Integer messageGood, Integer messageNum, Date createTime, Byte messageLevel, List<ReplyVO> reply) {
        this.nickname = nickname;
        this.messageId = messageId;
        this.messageTitle = messageTitle;
        this.messageContext = messageContext;
        this.messageGood = messageGood;
        this.messageNum = messageNum;
        this.createTime = createTime;
        this.messageLevel = messageLevel;
        this.reply = reply;
    }
}
