package com.bdx.message.entity.vo;

import java.util.Date;

/**
 * @author: 磊大大
 * @date: 2019/12/2 15:08
 */
public class ReplyVO {

    private String messageReplyText;
    private String NickName;
    private Date createTime;

    public String getMessageReplyText() {
        return messageReplyText;
    }

    public void setMessageReplyText(String messageReplyText) {
        this.messageReplyText = messageReplyText;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ReplyVO(String messageReplyText, String nickName, Date createTime) {
        this.messageReplyText = messageReplyText;
        NickName = nickName;
        this.createTime = createTime;
    }

    public ReplyVO() {
    }

    @Override
    public String toString() {
        return "ReplyVO{" +
                "messageReplyText='" + messageReplyText + '\'' +
                ", NickName='" + NickName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
