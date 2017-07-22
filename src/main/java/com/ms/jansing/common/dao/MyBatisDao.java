package com.ms.jansing.common.dao;

/**
 * Created by jansing on 17-7-22.
 */

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Component
public @interface MyBatisDao {
    String value() default "";
}