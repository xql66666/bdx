package constants;

/**
 * @author: 磊大大
 * @date: 2019/12/11 15:52
 */
public enum SourceTypeEnunm {
    //全部资源都属于的类别，通用
    COMMON((byte) 0,"全部"),
    //JAVA
    JAVA((byte) 1,"JAVA"),
    //大数据
    BIDDATA((byte) 2,"大数据"),
    //数据库
    DATABASE((byte) 3,"数据库"),
    //其他
    OTHER((byte) 99,"其他"),
    ;

    private byte type;

    private String desc;

    SourceTypeEnunm(byte type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
