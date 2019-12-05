package exception;

import constants.ResultCodeBase;

/**
 * @author: 磊大大
 * @date: 2019/10/15 10:51
 * 服务异常父类
 */
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 8670135969660230761L;

    private int code = ResultCodeBase.CODE_EXCEPTION;

    private Object data;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(int code) {
        super();
        this.code = code;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException() {

    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);

    }

    public ServiceException(int code, String message, Object data) {
        this(code, message);
        this.data = data;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }



    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
