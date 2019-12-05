package exception.core;


import exception.BaseException;

/**
 * 内部异常
 * 供开发快速定位
 */
public class InternalException extends BaseException {

	public InternalException() {
		super();
	}

	public InternalException(String message, Throwable cause,
                             boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause);
	}

	public InternalException(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalException(String message) {
		super(message);
	}

	public InternalException(Throwable cause) {
		super(cause);
	}

}
