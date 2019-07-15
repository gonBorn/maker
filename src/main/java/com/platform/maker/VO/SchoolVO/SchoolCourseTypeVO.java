package com.platform.maker.VO.SchoolVO;

import lombok.Data;

import java.util.List;

@Data
public class SchoolCourseTypeVO {
    private String processType;
    private List<SchoolCourseVO> courses;
}
