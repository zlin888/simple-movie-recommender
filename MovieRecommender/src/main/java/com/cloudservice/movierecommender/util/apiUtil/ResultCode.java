package com.cloudservice.movierecommender.util.apiUtil;

public enum ResultCode implements IErrorCode{

    // general status code
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    E_550(550,"电影不存在"),
    E_600(600, "用户不存在");

    // detailed code
    // example:
    // E_600(600, "用户不存在")


    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
