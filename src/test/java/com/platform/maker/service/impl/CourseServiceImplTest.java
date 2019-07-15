package com.platform.maker.service.impl;

import com.platform.maker.dataobject.Course;
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
public class CourseServiceImplTest {
    @Autowired
    private CourseServiceImpl courseService;
    @Test
    public void findOne() {
        Course course = courseService.findOne(1);
        Assert.assertEquals(new Integer(1),course.getCourseId());
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByTypeIdIn() {
    }

    @Test
    public void save() {
    }
    @Test
    public void findByInstitutionId() {
        List<Course> courseList = courseService.findByInstitutionId("123456");
        for (Course course:courseList){
            System.out.println(course.getCourseType());
        }
    }
}