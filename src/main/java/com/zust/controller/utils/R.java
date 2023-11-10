package com.zust.controller.utils;

import lombok.Data;

@Data
public class R {
    private boolean flag;
    private Object data;
    private String msg;

    public R(){}
    public R(boolean flag){
        this.flag =flag;
    }
    public R(String msg){
        this.flag=false;
        this.msg =msg;
    }
    public R(boolean flag,Object data){
        this.flag=flag;
        this.data=data;
    }

}
