package com.platform.maker.Enum;

import lombok.Getter;

@Getter
public enum CourseTypeEnum {
    PHYSICS(1,"物理"),
    ART(2,"艺术"),
    ENGLISH(3,"英语"),
    WORDS(4,"文学"),
    PC(5,"计算机"),
    ;
    private Integer code;

    private  String message;

    CourseTypeEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
