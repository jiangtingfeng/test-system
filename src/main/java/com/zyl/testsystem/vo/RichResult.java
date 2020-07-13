package com.zyl.testsystem.vo;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/14/014
 */

public class RichResult<T> {
    private Integer state;
    private String internalErrorCode;
    private String msg;
    private T results;

    public RichResult() {
        this.internalErrorCode = "0";
        this.msg = "success";
        this.state = 200;
    }

    public RichResult(T results) {
        this();
        this.setState(200);
        this.results = results;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResults() {
        return this.results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public String getInternalErrorCode() {
        return this.internalErrorCode;
    }

    public void setInternalErrorCode(String internalErrorCode) {
        this.internalErrorCode = internalErrorCode;
    }

    public RichResult(Integer errorCode, String errorMsg) {
        this.internalErrorCode = "0";
        this.msg = "success";
        this.state = errorCode;
        this.msg = errorMsg;
    }

    public RichResult(Integer state, String internalErrorCode, String errorMsg) {
        this.internalErrorCode = "0";
        this.msg = "success";
        this.state = state;
        this.internalErrorCode = internalErrorCode;
        this.msg = errorMsg;
    }

    public RichResult(BaseErrorCode baseErrorCode) {
        this.internalErrorCode = "0";
        this.msg = "success";
        this.state = baseErrorCode.getHttpCode();
        this.internalErrorCode = baseErrorCode.getInternalErrorCode();
        this.msg = baseErrorCode.getErrorMessage();
    }

    public RichResult(Throwable e) {
        this();
        if (e instanceof BaseException) {
            this.setMsg(((BaseException)e).getErrorMessage());
            this.setState(((BaseException)e).getHttpCode());
        } else {
            this.setMsg("未知异常");
            this.setState(500);
        }

    }
}
