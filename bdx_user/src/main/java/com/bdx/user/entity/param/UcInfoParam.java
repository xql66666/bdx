package com.bdx.user.entity.param;

import java.util.Date;

/**
 * @author: 磊大大
 * @date: 2019/11/20 13:37
 */
public class UcInfoParam {

    private String nickname;
    private Byte sex;
    private String userMail;
    private Date birthday;
    private String homecity;
    private String userQq;
    private String school;
    private int grade;
    private String introduce;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHomecity() {
        return homecity;
    }

    public void setHomecity(String homecity) {
        this.homecity = homecity;
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "UcInfoParam{" +
                "nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", userMail='" + userMail + '\'' +
                ", birthday=" + birthday +
                ", homecity='" + homecity + '\'' +
                ", userQq='" + userQq + '\'' +
                ", school='" + school + '\'' +
                ", grade=" + grade +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
