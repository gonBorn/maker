package com.platform.maker.service.impl;

import com.platform.maker.dataobject.CourseSchool;
import com.platform.maker.dataobject.Institution;
import com.platform.maker.repository.InstitutionRepository;
import com.platform.maker.service.CourseSchoolService;
import com.platform.maker.service.InstitutionService;
import com.platform.maker.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    @Autowired
    private InstitutionRepository repository;
    @Autowired
    private CourseSchoolService courseSchoolService;
    @Autowired
    private StudentCourseService studentCourseService;
    @Override
    public Institution findByInstitutionId(String institutionId) {
        return repository.findByInstitutionId(institutionId);
    }

    @Override
    public Integer findSchoolNum(Integer courseId) {
        return courseSchoolService.findByCourseId(courseId).size();
    }

    @Override
    public Integer findStuNum(Integer courseId) {
        List<CourseSchool> courseSchoolList = courseSchoolService.findByCourseId(courseId);
        Integer stuNum = 0;
        for (CourseSchool courseSchool:courseSchoolList) {
            stuNum += studentCourseService.stuNumInThisClass(courseId,courseSchool.getSchoolId());
        }
        return stuNum;
    }

}
