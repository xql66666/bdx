package constants;

/**
 * @author: 磊大大
 * @date: 2019/10/22 15:44
 */
public enum  UserIsTrueEnum {

    ISTRUE((byte) 1,"正常使用"),
    NOTRUE((byte) 0,"停用"),
    ;

    private byte status;

    private String desc;


    UserIsTrueEnum(byte status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public byte getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
