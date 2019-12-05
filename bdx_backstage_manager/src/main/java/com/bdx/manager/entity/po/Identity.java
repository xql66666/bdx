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
public class Identity {
    private int identityId;
    private String identityName;
    private String identityDesc;
    private byte identityFlag;
    private Date createTime;

    @Id
    @Column(name = "identity_id")
    public int getIdentityId() {
        return identityId;
    }

    public void setIdentityId(int identityId) {
        this.identityId = identityId;
    }

    @Basic
    @Column(name = "identity_name")
    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    @Basic
    @Column(name = "identity_desc")
    public String getIdentityDesc() {
        return identityDesc;
    }

    public void setIdentityDesc(String identityDesc) {
        this.identityDesc = identityDesc;
    }

    @Basic
    @Column(name = "identity_flag")
    public byte getIdentityFlag() {
        return identityFlag;
    }

    public void setIdentityFlag(byte identityFlag) {
        this.identityFlag = identityFlag;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity identity = (Identity) o;
        return identityId == identity.identityId &&
                identityFlag == identity.identityFlag &&
                Objects.equals(identityName, identity.identityName) &&
                Objects.equals(identityDesc, identity.identityDesc) &&
                Objects.equals(createTime, identity.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identityId, identityName, identityDesc, identityFlag, createTime);
    }
}
