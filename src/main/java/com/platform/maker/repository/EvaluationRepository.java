package com.platform.maker.repository;

import com.platform.maker.dataobject.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation,String> {
    //找到某学生的评价,假设学号唯一
    List<Evaluation> findByCourseIdAndStudentIdOrderByEvaluationTime(Integer courseId,String studentId);
    //找到某授课班
    List<Evaluation> findByCourseIdAndSchoolId(Integer courseId, String schoolId) ;

}
