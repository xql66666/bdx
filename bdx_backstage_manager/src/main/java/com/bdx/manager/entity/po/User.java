package com.bdx.manager.entity.po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @author: 磊大大
 * @date: 2019/10/12 17:26
 */
@Entity
public class User {
    private String userId;
    private String nickname;
    private String userPhone;
    //private String userQq;
    private String password;
    private byte identity; //1，普通  2，社员  3，管理  4，root
    private byte istrue;  //1,正常使用 0， 停用
    private byte complete; //1，完善   0， 未完善
    private String salt;
    private Date createTime;
    private Date lastUpdateTime;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "user_phone")
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

//    @Basic
//    @Column(name = "user_qq")
//    public String getUserQq() {
//        return userQq;
//    }
//
//    public void setUserQq(String userQq) {
//        this.userQq = userQq;
//    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "identity")
    public byte getIdentity() {
        return identity;
    }

    public void setIdentity(byte identity) {
        this.identity = identity;
    }

    @Basic
    @Column(name = "istrue")
    public byte getIstrue() {
        return istrue;
    }

    public void setIstrue(byte istrue) {
        this.istrue = istrue;
    }

    @Basic
    @Column(name = "complete")
    public byte getComplete() {
        return complete;
    }

    public void setComplete(byte complete) {
        this.complete = complete;
    }

    @Basic
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "last_update_time")
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                identity == user.identity &&
                istrue == user.istrue &&
                complete == user.complete &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(userPhone, user.userPhone) &&
                //Objects.equals(userQq, user.userQq) &&
                Objects.equals(password, user.password) &&
                Objects.equals(salt, user.salt) &&
                Objects.equals(createTime, user.createTime) &&
                Objects.equals(lastUpdateTime, user.lastUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, nickname, userPhone, /*userQq,*/ password, identity, istrue, complete, salt, createTime, lastUpdateTime);
    }
}
