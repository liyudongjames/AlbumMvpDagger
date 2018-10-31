package com.example.administrator.albumdemo.base;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
@Scope  //声明这是一个自定义@Scope注解
@Retention(RUNTIME)
public @interface ActivityScope {
}
