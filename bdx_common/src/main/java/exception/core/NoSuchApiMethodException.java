package exception.core;


import exception.BaseException;

/**
 * 没有api
 */
public class NoSuchApiMethodException extends BaseException {

    private String method;

    private String module;

    public NoSuchApiMethodException(String module, String method) {
        this.module = module;
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
