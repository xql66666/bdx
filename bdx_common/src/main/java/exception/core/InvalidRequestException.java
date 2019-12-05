package exception.core;


import exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 接受异常
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = " Invalid Request ! ")
public class InvalidRequestException extends BaseException {
    public  InvalidRequestException(String message) {
        super(message);
    }
}

