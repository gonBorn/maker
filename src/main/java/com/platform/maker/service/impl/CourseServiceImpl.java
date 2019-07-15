package com.platform.maker.service.impl;

import com.platform.maker.Enum.CourseTypeEnum;
import com.platform.maker.dataobject.Course;
import com.platform.maker.repository.CourseRepository;
import com.platform.maker.service.CourseService;
import com.platform.maker.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository repository;
    @Autowired
    private InstitutionService institutionService;
    @Override
    public Course findOne(Integer courseId) {
        return repository.findOne(courseId);
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Override
    public Course save(Course course) {
        return repository.save(course);
    }

    @Override
    public List<Course> findByCourseIdIn(List<Integer> courseList) {
        return repository.findByCourseIdIn(courseList);
    }

    @Override
    public List<Course> findByInstitutionId(String institutionId) {
        return repository.findByInstitutionId(institutionId);
    }

    @Override
    public String findInsName(Integer courseId) {
        String insId = repository.findByCourseId(courseId).getInstitutionId();
        return institutionService.findByInstitutionId(insId).getInstitutionName();
    }

    @Override
    public String findCourseName(Integer courseId) {
        return repository.findByCourseId(courseId).getCourseName();
    }

    @Override
    public List<Course> findByCourseType(Integer TypeId) {
        return repository.findByCourseType(TypeId);
    }

    @Override
    public String findCourseTypeByCourseId(Integer courseId) {
        Integer courseType = repository.findByCourseId(courseId).getCourseType();
        String courseT = "";
        for (CourseTypeEnum courseTypeEnum:CourseTypeEnum.values()){
            if (courseId==courseTypeEnum.getCode()) {
                courseT=courseTypeEnum.getMessage();
            }
        }
        return courseT;
    }
}
