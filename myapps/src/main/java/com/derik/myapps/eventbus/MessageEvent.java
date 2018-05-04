package com.derik.myapps.eventbus;

/**
 * Created by derik on 17-3-30.
 */

public class MessageEvent {
    private String msg;
    public MessageEvent(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
