package com.bdx.info.entity.vo;

import java.util.Date;

/**
 * @author: xuqianlei
 * @date: 2020/1/19 15:53
 * @version:
 */
public class InfoReplyVO {

    private String nickname;
    private String headImgUrl;
    private String infoReplyContent;
    private Date createTime;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getInfoReplyContent() {
        return infoReplyContent;
    }

    public void setInfoReplyContent(String infoReplyContent) {
        this.infoReplyContent = infoReplyContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public InfoReplyVO(String nickname, String headImgUrl, String infoReplyContent, Date createTime) {
        this.nickname = nickname;
        this.headImgUrl = headImgUrl;
        this.infoReplyContent = infoReplyContent;
        this.createTime = createTime;
    }
}
