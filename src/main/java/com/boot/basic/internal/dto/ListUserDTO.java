package com.boot.basic.internal.dto;

import com.boot.basic.common.constant.LocalDateTimeRegexpConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author HTuoZhou
 */
@Data
public class ListUserDTO implements Serializable {

    private static final long serialVersionUID = 1447638060760972322L;

    @ApiModelProperty(value = "开始时间", required = true)
    @NotNull(message = "开始时间不能为空")
    @Pattern(regexp = LocalDateTimeRegexpConstant.NORM_DATETIME_REGEXP, message = "开始时间格式不正确")
    private String startTime;

    @ApiModelProperty(value = "结束时间", required = true)
    @NotNull(message = "结束时间不能为空")
    @Pattern(regexp = LocalDateTimeRegexpConstant.NORM_DATETIME_REGEXP, message = "结束时间格式不正确")
    private String endTime;

}
