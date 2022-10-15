package com.boot.basic.internal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author HTuoZhou
 */
@Data
public class AddUserDTO implements Serializable {

    private static final long serialVersionUID = -248714653342768053L;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "姓名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

}
