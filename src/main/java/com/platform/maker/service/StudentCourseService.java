package com.platform.maker.service;

import com.platform.maker.dataobject.Course;
import com.platform.maker.dataobject.StudentCourse;

import java.util.List;

public interface StudentCourseService {
    List<StudentCourse> findStuCourseSelection(String schoolId, String studentId);
    List<StudentCourse> findStudents(Integer courseId,String schoolId);
    //获取某教学班学生人数
    Integer stuNumInThisClass (Integer courseId,String schoolId);
    List<StudentCourse> findByStudentId(String studentId);
    //判断课程是否已完结
    Boolean isCourseFinished (Integer courseId,String studentId);
    //该生可选的这个类型的课(该生未选过但是学校有的此类型的课)
    List<Integer> findThisTypeThisStuNotSelect(Integer typeId,String studentId);
    //获取课程名
    String findCourseName(Integer courseId);
    StudentCourse save(StudentCourse studentCourse);
}
