package com.platform.maker.Enum;

public enum WeekdayEnum {
    MON(1,"星期一"),
    TUE(2,"星期二"),
    WED(3,"星期三"),
    THU(4,"星期四"),
    FRI(5,"星期五"),
    SAT(6,"星期六"),
    SUN(7,"星期日")
    ;

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    WeekdayEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
