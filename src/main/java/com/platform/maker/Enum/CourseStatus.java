package com.platform.maker.Enum;

public enum CourseStatus {
    CONTONUE(0,"进行中"),
    FINISH(1,"已结课")
    ;

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    CourseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
