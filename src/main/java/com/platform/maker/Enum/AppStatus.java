package com.platform.maker.Enum;

import lombok.Getter;

@Getter
public enum AppStatus {
    WAIT(0,"审核中"),
    PASS(1,"通过"),
    FAIL(2,"否决")
    ;
    private Integer code;

    private  String message;

    AppStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
