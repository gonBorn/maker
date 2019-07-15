package com.platform.maker.VO.StuVO;

import lombok.Data;

import java.util.Date;

@Data
public class StuCourseInfoVO {

    private String teacherName;

    private String courseName;

    private Integer courseId;

    private String icon;

    private String address;
    /** 默认每周只上一次课，周几上课，枚举 */
    private String day;

    private Integer currentWeek;

    private Integer totalWeek;
}
