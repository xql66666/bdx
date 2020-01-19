package com.bdx.info.entity.po;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author: 磊大大
 * @date: 2019/12/31 14:07
 */
@Entity
@Table(name = "info_reply", schema = "bdx", catalog = "")
public class InfoReply {
    private int id;
    private Integer infoId;
    private String infoReplyUser;
    private String infoReplyContent;
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
    @Column(name = "info_id")
    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    @Basic
    @Column(name = "info_reply_user")
    public String getInfoReplyUser() {
        return infoReplyUser;
    }

    public void setInfoReplyUser(String infoReplyUser) {
        this.infoReplyUser = infoReplyUser;
    }

    @Basic
    @Column(name = "info_reply_content")
    public String getInfoReplyContent() {
        return infoReplyContent;
    }

    public void setInfoReplyContent(String infoReplyContent) {
        this.infoReplyContent = infoReplyContent;
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
        InfoReply infoReply = (InfoReply) o;
        return id == infoReply.id &&
                Objects.equals(infoId, infoReply.infoId) &&
                Objects.equals(infoReplyUser, infoReply.infoReplyUser) &&
                Objects.equals(infoReplyContent, infoReply.infoReplyContent) &&
                Objects.equals(createTime, infoReply.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, infoId, infoReplyUser, infoReplyContent, createTime);
    }
}
