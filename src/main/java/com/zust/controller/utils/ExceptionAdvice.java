package com.zust.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public R doException(Exception ex){
        //记录日志
        //通知运维
        //通知开发
        ex.printStackTrace();
        return new R("出现异常啦");
    }
}
