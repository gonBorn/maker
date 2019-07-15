package com.platform.maker.service;

import com.platform.maker.dataobject.Teacher;

import java.util.List;

public interface TeacherService {
    String findTeacherName(Integer courseId,String studentId);
    String findTeacherName(String teacherId);
    List<Teacher> findByInstitutionId(String institutionId);
    Teacher findOne(String teacherId);
}
