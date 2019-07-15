package com.platform.maker.VO.TeacherVO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Data
public class TeacherCourseVO {
    private String schoolId;
    private String schoolName;
    private Integer courseId;
    private String courseName;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date endTime;
    private String address;
    private Integer stuNum;
    private Integer currentWeeek;
    private Integer period;
    private String weekday;
}
