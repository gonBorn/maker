package com.platform.maker.Enum;

public enum SchoolTypeEnum {
    PRIMARY(1,"小学"),
    JUNIOR(2,"初中"),
    SENIOR(3,"高中")
    ;

    private Integer code;
    private String message;

    SchoolTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
