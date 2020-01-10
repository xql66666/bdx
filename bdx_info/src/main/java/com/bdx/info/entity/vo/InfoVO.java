package com.bdx.info.entity.vo;

import java.util.Date;

/**
 * @author: 磊大大
 * @date: 2020/1/10 10:15
 */
public class InfoVO {
    private int id;
    private String nickname;
    private String infoName;
    private String infoContent;
    private String infoUser;
    private Integer infoReplyNum;
    private Integer infoGood;
    private Byte infoType;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public String getInfoUser() {
        return infoUser;
    }

    public void setInfoUser(String infoUser) {
        this.infoUser = infoUser;
    }

    public Integer getInfoReplyNum() {
        return infoReplyNum;
    }

    public void setInfoReplyNum(Integer infoReplyNum) {
        this.infoReplyNum = infoReplyNum;
    }

    public Integer getInfoGood() {
        return infoGood;
    }

    public void setInfoGood(Integer infoGood) {
        this.infoGood = infoGood;
    }

    public Byte getInfoType() {
        return infoType;
    }

    public void setInfoType(Byte infoType) {
        this.infoType = infoType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public InfoVO(int id, String nickname, String infoName, String infoContent, String infoUser, Integer infoReplyNum, Integer infoGood, Byte infoType, Date createTime) {
        this.id = id;
        this.nickname = nickname;
        this.infoName = infoName;
        this.infoContent = infoContent;
        this.infoUser = infoUser;
        this.infoReplyNum = infoReplyNum;
        this.infoGood = infoGood;
        this.infoType = infoType;
        this.createTime = createTime;
    }

    public InfoVO(int id, String nickname, String infoName, String infoContent, Integer infoReplyNum, Integer infoGood, Byte infoType, Date createTime) {
        this.id = id;
        this.nickname = nickname;
        this.infoName = infoName;
        this.infoContent = infoContent;
        this.infoReplyNum = infoReplyNum;
        this.infoGood = infoGood;
        this.infoType = infoType;
        this.createTime = createTime;
    }
}
