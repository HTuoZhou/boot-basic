package com.boot.basic.internal.vo;

import com.boot.basic.internal.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author HTuoZhou
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserVO extends User implements Serializable {
    private static final long serialVersionUID = -7302488603831633263L;
}
