package com.bdx.manager.entity.vo;

import java.util.Date;

/**
 * @author: 磊大大
 * @date: 2019/10/21 16:04
 *
 * 转换类 把User类属性得密码去掉 变为UserVO传给前端
 */
public class UserVO {
    private String userId;
    private String nickname;
    private String userPhone;
    //private String userQq;
    private byte identity;
    private byte istrue;
    private byte complete;
    private String salt;
    private Date createTime;
    private Date lastUpdateTime;

    public UserVO() {
    }

    public UserVO(String userId, String nickname, String userPhone, /*String userQq,*/ byte identity, byte istrue, byte complete, String salt, Date createTime, Date lastUpdateTime) {
        this.userId = userId;
        this.nickname = nickname;
        this.userPhone = userPhone;
       // this.userQq = userQq;
        this.identity = identity;
        this.istrue = istrue;
        this.complete = complete;
        this.salt = salt;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

//    public String getUserQq() {
//        return userQq;
//    }
//
//    public void setUserQq(String userQq) {
//        this.userQq = userQq;
//    }

    public byte getIdentity() {
        return identity;
    }

    public void setIdentity(byte identity) {
        this.identity = identity;
    }

    public byte getIstrue() {
        return istrue;
    }

    public void setIstrue(byte istrue) {
        this.istrue = istrue;
    }

    public byte getComplete() {
        return complete;
    }

    public void setComplete(byte complete) {
        this.complete = complete;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
