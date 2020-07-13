package com.zyl.testsystem.vo;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/14/014
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer httpCode;
    private String errorMessage;
    private String internalErrorCode;

    public BaseException() {
        this.httpCode = 500;
    }

    public BaseException(String message) {
        super(message);
        this.httpCode = 500;
        this.errorMessage = message;
    }

    public BaseException(Throwable cause) {
        super(cause);
        this.httpCode = 500;
        this.errorMessage = cause.getMessage();
    }

    public BaseException(BaseErrorCode errorCode) {
        this(errorCode.getHttpCode(), errorCode.getErrorMessage(), errorCode.getInternalErrorCode());
    }

    public BaseException(String errMsg, String internalErrorCode) {
        super(errMsg);
        this.httpCode = 500;
        this.errorMessage = errMsg;
        this.internalErrorCode = internalErrorCode;
    }

    public BaseException(Integer httpCode, String errMsg, String internalErrorCode) {
        super(errMsg);
        this.httpCode = 500;
        this.httpCode = httpCode;
        this.errorMessage = errMsg;
        this.internalErrorCode = internalErrorCode;
    }

    public Integer getHttpCode() {
        return this.httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getInternalErrorCode() {
        return this.internalErrorCode;
    }

    public void setInternalErrorCode(String internalErrorCode) {
        this.internalErrorCode = internalErrorCode;
    }

    @Override
    public String toString() {
        return "BaseException{httpCode=" + this.httpCode + ", errorMessage='" + this.errorMessage + '\'' + ", internalErrorCode='" + this.internalErrorCode + '\'' + '}';
    }
}
