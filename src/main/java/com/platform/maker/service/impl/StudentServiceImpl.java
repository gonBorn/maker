package com.platform.maker.service.impl;

import com.platform.maker.dataobject.Student;
import com.platform.maker.repository.StudentRepository;
import com.platform.maker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;
    @Override
    public Student findOne(String studentId) {
        return repository.findOne(studentId);
    }

    @Override
    public String findSchoolId(String studentId) {
        return repository.findByStudentId(studentId).getSchoolId();
    }
}
