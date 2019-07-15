package com.platform.maker.service;

import com.platform.maker.dataobject.Course;

import java.util.List;

public interface CourseService {
    Course findOne(Integer courseId);
    List<Course> findAll();
    List<Course> findByCourseIdIn(List<Integer> courseList);
    Course save(Course course);
    List<Course> findByInstitutionId(String institutionId);
    //查找机构名
    String findInsName(Integer courseId);
    String findCourseName(Integer courseId);
    List<Course> findByCourseType(Integer TypeId);
    //以字符串形式输出课程类型
    String findCourseTypeByCourseId(Integer courseId);
}
