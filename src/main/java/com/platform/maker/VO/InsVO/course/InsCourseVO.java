package com.platform.maker.VO.InsVO.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InsCourseVO {
    private String typeName;
    private Integer type;
    @JsonProperty("courses")
    private List<InsCourseInfo> insCourseInfos;
}
