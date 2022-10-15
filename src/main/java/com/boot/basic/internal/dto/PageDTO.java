package com.boot.basic.internal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author HTuoZhou
 */
@Data
public class PageDTO implements Serializable {

    private static final long serialVersionUID = 588907123072550221L;

    @ApiModelProperty(value = "当前页码", required = true)
    @NotNull(message = "当前页码不能为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页大小", required = true)
    @NotNull(message = "每页大小不能为空")
    private Integer pageSize;
}
