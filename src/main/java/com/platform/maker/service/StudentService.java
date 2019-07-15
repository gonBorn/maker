package com.platform.maker.service;

import com.platform.maker.dataobject.Student;

public interface StudentService {
    Student findOne(String studentId);
    //获取学校id
    String findSchoolId(String studentId);
}
