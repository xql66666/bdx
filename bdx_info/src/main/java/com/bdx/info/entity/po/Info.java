package com.bdx.info.entity.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author: 磊大大
 * @date: 2019/12/31 14:07
 */
@Entity
public class Info {
    private int id;
    private String infoName;
    private String infoContent;
    private String infoUser;
    private Integer infoReplyNum;
    private Integer infoGood;
    private Byte infoType;
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
    @Column(name = "info_name")
    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    @Basic
    @Column(name = "info_content")
    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    @Basic
    @Column(name = "info_user")
    public String getInfoUser() {
        return infoUser;
    }

    public void setInfoUser(String infoUser) {
        this.infoUser = infoUser;
    }

    @Basic
    @Column(name = "info_reply_num")
    public Integer getInfoReplyNum() {
        return infoReplyNum;
    }

    public void setInfoReplyNum(Integer infoReplyNum) {
        this.infoReplyNum = infoReplyNum;
    }

    @Basic
    @Column(name = "info_good")
    public Integer getInfoGood() {
        return infoGood;
    }

    public void setInfoGood(Integer infoGood) {
        this.infoGood = infoGood;
    }

    @Basic
    @Column(name = "info_type")
    public Byte getInfoType() {
        return infoType;
    }

    public void setInfoType(Byte infoType) {
        this.infoType = infoType;
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
        Info info = (Info) o;
        return id == info.id &&
                Objects.equals(infoName, info.infoName) &&
                Objects.equals(infoContent, info.infoContent) &&
                Objects.equals(infoUser, info.infoUser) &&
                Objects.equals(infoReplyNum, info.infoReplyNum) &&
                Objects.equals(infoGood, info.infoGood) &&
                Objects.equals(infoType, info.infoType) &&
                Objects.equals(createTime, info.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, infoName, infoContent, infoUser, infoReplyNum, infoGood, infoType, createTime);
    }
}
