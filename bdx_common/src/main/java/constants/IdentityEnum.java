package constants;

/**
 * @author: 磊大大
 * @date: 2019/10/15 10:29
 * 身份类型
 */
public enum IdentityEnum {
    //普通用户
    ORDINARY((byte) 1,"ordinary"),
    //北斗星社员
    MEMBER((byte) 2,"member"),
    //北斗星管理
    MANAGE((byte) 3,"manage"),
    //最高权限
    ROOT((byte) 4,"root"),
    ;

    private byte status;

    private String desc;

    IdentityEnum(byte status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
