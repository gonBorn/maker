package com.platform.maker.VO.StuVO;

import lombok.Data;

import java.util.List;
@Data
public class EvaluationVO {
    private String courseName;
    private DetailEvaluationVO myAverage;
    private DetailEvaluationVO classAverage;
    private List<DetailEvaluationVO> allMyEvaluation;
}
