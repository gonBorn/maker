package com.platform.maker.VO.InsVO.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InsCourseInfo {
    private String courseName;
    private String description;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date createTime;
    private Integer period;
    private String crowd;
    private Integer num;
    private List<SchoolsVO> schools;
}
