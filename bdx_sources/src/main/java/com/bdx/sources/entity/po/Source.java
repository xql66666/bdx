package com.bdx.sources.entity.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author: 磊大大
 * @date: 2019/12/11 15:51
 */
@Entity
public class Source {
    private int id;
    private String sourceId;
    private String sourceName;
    private String userId;
    private String sourceUrl;
    private String sourcePwd;
    private String sourceType;
    private byte sourceIstrue;
    private Timestamp createTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "source_id")
    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Basic
    @Column(name = "source_name")
    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
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
    @Column(name = "source_url")
    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Basic
    @Column(name = "source_pwd")
    public String getSourcePwd() {
        return sourcePwd;
    }

    public void setSourcePwd(String sourcePwd) {
        this.sourcePwd = sourcePwd;
    }

    @Basic
    @Column(name = "source_type")
    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    @Basic
    @Column(name = "source_istrue")
    public byte getSourceIstrue() {
        return sourceIstrue;
    }

    public void setSourceIstrue(byte sourceIstrue) {
        this.sourceIstrue = sourceIstrue;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return id == source.id &&
                sourceIstrue == source.sourceIstrue &&
                Objects.equals(sourceId, source.sourceId) &&
                Objects.equals(sourceName, source.sourceName) &&
                Objects.equals(userId, source.userId) &&
                Objects.equals(sourceUrl, source.sourceUrl) &&
                Objects.equals(sourcePwd, source.sourcePwd) &&
                Objects.equals(sourceType, source.sourceType) &&
                Objects.equals(createTime, source.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceId, sourceName, userId, sourceUrl, sourcePwd, sourceType, sourceIstrue, createTime);
    }
}
