package com.bdx.info.entity.param;

import java.io.Serializable;

/**
 * @author: 磊大大
 * @date: 2019/12/31 14:20
 */
public class InfoAddParam implements Serializable {

    private String infoName;
    private String infoType;
    private String infoContent;

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }
}
