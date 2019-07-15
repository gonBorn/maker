package com.platform.maker.service.impl;

import com.platform.maker.dataobject.Evaluation;
import com.platform.maker.repository.EvaluationRepository;
import com.platform.maker.service.EvaluationService;
import com.platform.maker.service.StudentCourseService;
import com.platform.maker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationRepository repository;
    @Autowired
    private StudentCourseService studentCourseService;
    @Autowired
    private StudentService studentService;

    //查找某一学生某门课的所有评价

    @Override
    public List<Evaluation> findOneStuEvaluation(Integer courseId, String studentId) {
        return repository.findByCourseIdAndStudentIdOrderByEvaluationTime(courseId,studentId);
    }

    //查找某门课某个指标的平均水平

    @Override
    public Integer classAveragePositive(Integer courseId,String schoolId) {
        List<Evaluation> classEvaList = repository.findByCourseIdAndSchoolId(courseId,schoolId);
        Integer totalScore = 0;
        for (Evaluation evaluation:classEvaList) {
            totalScore+=evaluation.getPositive();
        }
        if (classEvaList.size()!=0) {
            return totalScore/classEvaList.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public Integer classAverageTeamwork(Integer courseId, String schoolId) {
        List<Evaluation> classEvaList = repository.findByCourseIdAndSchoolId(courseId,schoolId);
        Integer totalScore = 0;
        for (Evaluation evaluation:classEvaList) {
            totalScore+=evaluation.getTeamwork();
        }
        if (classEvaList.size()!=0) {
            return totalScore/classEvaList.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public Integer classAverageLeadership(Integer courseId, String schoolId) {
        List<Evaluation> classEvaList = repository.findByCourseIdAndSchoolId(courseId,schoolId);
        Integer totalScore = 0;
        for (Evaluation evaluation:classEvaList) {
            totalScore+=evaluation.getLeadership();
        }
        if (classEvaList.size()!=0) {
            return totalScore/classEvaList.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public Integer classAveragePractical(Integer courseId, String schoolId) {
        List<Evaluation> classEvaList = repository.findByCourseIdAndSchoolId(courseId,schoolId);
        Integer totalScore = 0;
        for (Evaluation evaluation:classEvaList) {
            totalScore+=evaluation.getPractical();
        }
        if (classEvaList.size()!=0) {
            return totalScore/classEvaList.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public Integer classAverageCreative(Integer courseId, String schoolId) {
        List<Evaluation> classEvaList = repository.findByCourseIdAndSchoolId(courseId,schoolId);
        Integer totalScore = 0;
        for (Evaluation evaluation:classEvaList) {
            totalScore+=evaluation.getCreative();
        }
        if (classEvaList.size()!=0) {
            return totalScore/classEvaList.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public List<Integer> myAverage(Integer courseId, String studentId) {
        String schoolId = studentService.findOne(studentId).getSchoolId();
        List<Evaluation> myEvaList = repository.findByCourseIdAndStudentIdOrderByEvaluationTime(courseId,studentId);
        Integer stuNum = studentCourseService.stuNumInThisClass(courseId,schoolId);
        List<Integer> myEvaItemList = new ArrayList<>();
        Integer practical = 0;
        Integer positive = 0;
        Integer teamwork = 0;
        Integer leadership = 0;
        Integer creative = 0;
        //按一下顺序存入数组
        //practical    0
        //positive      1
        //teamwork      2
        //leadership   3
        //creative  4
        for (Evaluation eva:myEvaList) {
            practical += eva.getPractical();
            positive += eva.getPositive();
            teamwork += eva.getTeamwork();
            leadership += eva.getLeadership();
            creative += eva.getCreative();
        }
        int evaTimes = myEvaList.size();
        if (evaTimes!=0){
            myEvaItemList.add(practical/evaTimes);
            myEvaItemList.add(positive/evaTimes);
            myEvaItemList.add(teamwork/evaTimes);
            myEvaItemList.add(leadership/evaTimes);
            myEvaItemList.add(creative/evaTimes);
        }

        return myEvaItemList;
    }

    @Override
    public Evaluation save(Evaluation evaluation) {
        return repository.save(evaluation);
    }

    @Override
    public Date lastEvaTime(Integer courseId, String studentId) {
        return repository.findByCourseIdAndStudentIdOrderByEvaluationTime(courseId,studentId).get(0).getEvaluationTime();
    }
}
