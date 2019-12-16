package com.bdx.sources.entity.vo;

/**
 * @author: 磊大大
 * @date: 2019/12/16 10:57
 */
public class MySourceVO {

    private String sourceId;
    private String sourceName;
    private String sourceUrl;
    private String sourcePwd;
    private String[] sourceType;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

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

    public String[] getSourceType() {
        return sourceType;
    }

    public void setSourceType(String[] sourceType) {
        this.sourceType = sourceType;
    }

    public MySourceVO(String sourceName, String sourceUrl, String sourcePwd, String[] sourceType) {
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
        this.sourcePwd = sourcePwd;
        this.sourceType = sourceType;
    }

    public MySourceVO(String sourceId, String sourceName, String sourceUrl, String sourcePwd, String[] sourceType) {
        this.sourceId = sourceId;
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
        this.sourcePwd = sourcePwd;
        this.sourceType = sourceType;
    }
}
