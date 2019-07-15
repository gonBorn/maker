package com.platform.maker.VO.InsVO.Cou;

import lombok.Data;

import java.util.List;

@Data
public class InsCouListVO {
    private String courseName;
    private String description;
    private Integer stuNum;
    private Integer schoolNum;
    private String courseType;
    private List<SchoolsVO> schoolsVOS;
}
