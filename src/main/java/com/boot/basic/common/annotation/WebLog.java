package com.boot.basic.common.annotation;

import java.lang.annotation.*;

/**
 * @author HTuoZhou
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
}
