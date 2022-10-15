package com.boot.basic.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HTuoZhou
 */
@Data
public class BusinessException extends RuntimeException {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    public BusinessException() {
        this.code = ResultCodeEnum.ERROR.getCode();
        this.message = ResultCodeEnum.ERROR.getMessage();
    }

    public BusinessException(Object data) {
        this.code = ResultCodeEnum.ERROR.getCode();
        this.message = ResultCodeEnum.ERROR.getMessage();
        this.data = data;
    }

    public BusinessException(ResultCodeEnum resultCodeEnum, Object data) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.data = data;
    }
}
