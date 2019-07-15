package com.platform.maker.VO.InsVO.Cou;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.platform.maker.dataobject.Student;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SchoolsVO {
    private String schoolName;
    private Integer stuNum;
    private List<String> studentNames;
}
