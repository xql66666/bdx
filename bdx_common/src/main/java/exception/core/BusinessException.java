package exception.core;

import exception.BaseException;

/**
 * 业务异常
 */
public class BusinessException extends BaseException {

    public BusinessException(int code) {
        super(code);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(code, message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(int code, Throwable cause) {
        super(code, cause);
    }

    /**
     * checked异常不记录栈异常详细信息
     */
    @Override
    public synchronized Throwable fillInStackTrace() {

        return this;
    }

}
