package com.bdx.sources.entity.param;

/**
 * @author: 磊大大
 * @date: 2019/12/11 15:16
 * 添加资源入参
 */
public class SourceParam {

    private String sourceName;
    private String sourceUrl;
    private String sourcePwd;
    private Integer[] sourceType;

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

    public Integer[] getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer[] sourceType) {
        this.sourceType = sourceType;
    }
}
