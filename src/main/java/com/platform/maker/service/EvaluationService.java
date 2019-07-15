package com.platform.maker.service;

import com.platform.maker.dataobject.Evaluation;

import java.util.Date;
import java.util.List;

public interface EvaluationService {
    //查找某一学生某门课的所有评价
    List<Evaluation> findOneStuEvaluation(Integer courseId,String studentId);
    //查找某门课某个指标全班的平均水平
    Integer classAveragePositive(Integer courseId,String schoolId);
    Integer classAverageTeamwork(Integer courseId,String schoolId);
    Integer classAverageLeadership(Integer courseId,String schoolId);
    Integer classAveragePractical(Integer courseId,String schoolId);
    Integer classAverageCreative(Integer courseId,String schoolId);

    List<Integer> myAverage(Integer courseId,String studentId);
    Evaluation save(Evaluation evaluation);
    Date lastEvaTime(Integer courseId,String studentId);
}
