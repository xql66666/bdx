package exception;

/**
 * @author: 磊大大
 * @date: 2019/10/15 10:53
 */
public class BaseException extends ServiceException {
    public BaseException() {
        super();
    }

    public BaseException(int code) {
        super(code);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(int code, String message) {
        super(code, message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(int code, Throwable cause) {
        super(code, cause);
    }
}
