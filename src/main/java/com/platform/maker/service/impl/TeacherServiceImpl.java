package com.platform.maker.service.impl;

import com.platform.maker.dataobject.Teacher;
import com.platform.maker.repository.TeacherRepository;
import com.platform.maker.service.CourseSchoolService;
import com.platform.maker.service.StudentService;
import com.platform.maker.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository repository;
    @Autowired
    private CourseSchoolService courseSchoolService;
    @Autowired
    private StudentService studentService;


    @Override
    public List<Teacher> findByInstitutionId(String institutionId) {
        return repository.findByInstitutionId(institutionId);
    }

    @Override
    public String findTeacherName(Integer courseId, String studentId) {
        String schoolId = studentService.findOne(studentId).getSchoolId();
        String teacherId = courseSchoolService.findOne(courseId,schoolId).getTeacherId();
        return repository.findByTeacherId(teacherId).getTeacherName();
    }

    @Override
    public String findTeacherName(String teacherId) {
        return repository.findByTeacherId(teacherId).getTeacherName();
    }

    @Override
    public Teacher findOne(String teacherId) {
        return repository.findByTeacherId(teacherId);
    }
}
