package exception.core;


import exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 未登录
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = " Invalid Request ! ")
public class NotLoginException extends BaseException {
    public NotLoginException(String message) {
        super(message);
    }
}

