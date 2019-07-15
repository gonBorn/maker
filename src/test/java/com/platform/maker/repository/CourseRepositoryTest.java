package com.platform.maker.repository;

import com.platform.maker.dataobject.Course;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository repository;

//    @Test
//    public void findOneTest(){
//        Course course = repository.findOne(1);
//        System.out.println(course.toString());
//    }
    @Test
    //@Transactional
    public void saveTest(){
        Course course = new Course();
        course.setCourseName("陶艺");
        course.setCourseType(2);
        course.setDescription("让心灵沉淀下来");
        course.setCrowd("高中");
        course.setPeriod(20);
        course.setInstitutionId("123456");
        Course res = repository.save(course);
        Assert.assertNotNull(res);
    }
    @Test
    public void findByInstitutionIdTest() {
        PageRequest request = new PageRequest(0,1);
        Page<Course> res = repository.findByInstitutionId("123456", request);
        System.out.println(res.getTotalPages());
    }
    @Test
    public void findByInstitutionIdTestTwo() {
        List<Course> res = repository.findByInstitutionId("123456");
        ArrayList<Integer> courselist = new ArrayList<>();
        for (int i=0;i<res.size();i++){
            courselist.add(res.get(i).getCourseId());
        }
        System.out.println(courselist.size());

    }
}