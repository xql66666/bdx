package com.bdx.manager.entity.po;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author: 磊大大
 * @date: 2019/10/12 17:26
 */
@Entity
@Table(name = "user_info", schema = "bdx", catalog = "")
public class UserInfo {
    private int userInfoId;
    private String userId;
    private String nickname;
    private String headImgUrl;
    private Byte sex;
    //private String userPhone;
    private String userMail;
    private Date birthday;
    private String homecity;
    private String userQq;
    private String school;
    private int grade;
    private String introduce;
    private Date createTime;
    private Date lastUpdataTime;

    @Id
    @Column(name = "user_info_id")
    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
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
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "head_img_url")
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    @Basic
    @Column(name = "sex")
    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

//    @Basic
//    @Column(name = "user_phone")
//    public String getUserPhone() {
//        return userPhone;
//    }
//
//    public void setUserPhone(String userPhone) {
//        this.userPhone = userPhone;
//    }

    @Basic
    @Column(name = "user_mail")
    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "homecity")
    public String getHomecity() {
        return homecity;
    }

    public void setHomecity(String homecity) {
        this.homecity = homecity;
    }

    @Basic
    @Column(name = "user_qq")
    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "grade")
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "introduce")
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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
    @Column(name = "last_updata_time")
    public Date getLastUpdataTime() {
        return lastUpdataTime;
    }

    public void setLastUpdataTime(Date lastUpdataTime) {
        this.lastUpdataTime = lastUpdataTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return userInfoId == userInfo.userInfoId &&
                userId == userInfo.userId &&
                Objects.equals(nickname, userInfo.nickname) &&
                Objects.equals(headImgUrl, userInfo.headImgUrl) &&
                Objects.equals(sex, userInfo.sex) &&
                //Objects.equals(userPhone, userInfo.userPhone) &&
                Objects.equals(birthday, userInfo.birthday) &&
                Objects.equals(homecity, userInfo.homecity) &&
                Objects.equals(userQq, userInfo.userQq) &&
                Objects.equals(school, userInfo.school) &&
                Objects.equals(grade, userInfo.grade) &&
                Objects.equals(introduce, userInfo.introduce) &&
                Objects.equals(createTime, userInfo.createTime) &&
                Objects.equals(lastUpdataTime, userInfo.lastUpdataTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userInfoId, userId, nickname, headImgUrl, sex, /*userPhone,*/ birthday, homecity, userQq, school, grade, introduce, createTime, lastUpdataTime);
    }
}
