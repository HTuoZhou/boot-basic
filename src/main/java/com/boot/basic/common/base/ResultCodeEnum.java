package com.boot.basic.common.base;

/**
 * @author HTuoZhou
 */
public enum ResultCodeEnum {

    /**
     * 请求成功
     */
    SUCCESS(200, "成功"),
    /**
     * 未认证
     */
    FAILED_UNAUTHEN(401, "未认证"),
    /**
     * 未授权
     */
    FAILED_UNAUTHOR(403, "未授权"),
    /**
     * 请求失败
     */
    ERROR(500, "失败"),
    /**
     * 参数校验异常
     */
    FAILED_PARAMETER(600, "参数校验异常"),
    /**
     * 模板不正确
     */
    FAILED_TEMPLATE(601, "模板不正确"),
    /**
     * 模板导入失败
     */
    FAILED_TEMPLATE_IMPORT(602, "模板导入失败"),
    /**
     * 文件大小限制异常
     */
    FAILED_FILE_SIZE_LIMIT(603, "文件大小限制异常");


    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 状态描述信息
     */
    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
