package com.platform.maker.repository;

import com.platform.maker.dataobject.StudentCourse;
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
public class StudentCourseRepositoryTest {
    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Test
    public void saveTest(){
        StudentCourse studentCourse = new StudentCourse(2,"123","2151601038");
        StudentCourse res = studentCourseRepository.save(studentCourse);
        Assert.assertNotNull(res);
    }

    @Test
    public void findByStudentIdTest() {
        List<StudentCourse> res = studentCourseRepository.findByStudentId("2151601038");
        Assert.assertNotNull(res);
    }
}