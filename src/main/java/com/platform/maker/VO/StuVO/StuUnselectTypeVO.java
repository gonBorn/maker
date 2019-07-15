package com.platform.maker.VO.StuVO;

import lombok.Data;

import java.util.List;
@Data
public class StuUnselectTypeVO {
    private Integer TypeId;
    private String TypeName;
    private List<StuUnselectedCourseVO> stuUnselectedCourseVOS;
}
