package com.platform.maker.service.impl;

import com.platform.maker.dataobject.StudentCourse;
import com.platform.maker.service.StudentCourseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentCourseServiceImplTest {
    @Autowired
    private StudentCourseService studentCourseService;
    @Test
    public void findByStudentId() {
        List<StudentCourse> courseList = studentCourseService.findByStudentId("2151601038");
        Assert.assertNotEquals(0,courseList.size());
    }
}