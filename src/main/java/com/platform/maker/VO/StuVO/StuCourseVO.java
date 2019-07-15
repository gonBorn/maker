package com.platform.maker.VO.StuVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class StuCourseVO {

    private String typeName;

    private Integer type;
    @JsonProperty("courses")
    private List<StuCourseInfoVO> stuCourseInfoVOList;
}
