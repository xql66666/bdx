package com.bdx.sources.entity.vo;

import com.bdx.sources.entity.po.User;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * @author: 磊大大
 * @date: 2019/12/12 10:27
 */
public class SourceDTO {
    private String sourceName;
    private String sourceUrl;
    private String sourcePwd;
    private String sourceType;
   // private String nickname;


    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourcePwd() {
        return sourcePwd;
    }

    public void setSourcePwd(String sourcePwd) {
        this.sourcePwd = sourcePwd;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }


//    public SourceDTO(String sourceName, String sourceUrl, String sourcePwd, String sourceType, String nickname) {
//        this.sourceName = sourceName;
//        this.sourceUrl = sourceUrl;
//        this.sourcePwd = sourcePwd;
//        this.sourceType = sourceType;
//        this.nickname = nickname;
//    }
}
