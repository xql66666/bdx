package entity;

import java.io.Serializable;

/**
 * @author: 磊大大
 * @date: 2019/10/12 15:13
 * HTTP接口返回对象
 */
public class ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = -1961461128479990688L;

    private int code; // 状态码
    private String msg; // 返回信息
    private T data; // 返回具体对象信息

    //private String tid;

    public ResponseEntity() {
    }

    public ResponseEntity(int code) {
        this.setCode(code);
    }

    public ResponseEntity(int code, String message) {
        this.setCode(code);
        this.setMsg(message);
    }

    public ResponseEntity(int code, String message, T data) {
        this(code, message);
        this.setData(data);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /*public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }*/
}
