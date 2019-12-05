package exception.core;


import exception.BaseException;

/**
 *Token错误
 */
public class TokenErrorException extends BaseException {
    public TokenErrorException() {
        super();
    }
    public TokenErrorException(String message) {
        super(message);
    }
}
