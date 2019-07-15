package com.platform.maker.VO.SchoolVO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SchoolCourseVO {
    private String courseName;
    private String courseType;
    private Integer period;
    private Integer stuNum;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date endTime;
}
