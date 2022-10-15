package com.boot.basic.internal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author HTuoZhou
 */
@Data
public class FileDownloadDTO implements Serializable {

    private static final long serialVersionUID = -1689401793802564785L;

    @ApiModelProperty(value = "下载文件路径", required = true)
    @NotBlank(message = "下载文件路径不能为空")
    private String downloadFilePath;

}
