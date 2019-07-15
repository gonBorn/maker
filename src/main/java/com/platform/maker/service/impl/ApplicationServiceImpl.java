package com.platform.maker.service.impl;

import com.platform.maker.Enum.AppStatus;
import com.platform.maker.dataobject.Application;
import com.platform.maker.repository.ApplicationRepository;
import com.platform.maker.service.ApplicationService;
import com.platform.maker.service.CourseService;
import com.platform.maker.service.SchoolService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository repository;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private CourseService courseService;
    @Override
    public List<Application> findByCourseList(List<Integer> courseList) {
        return repository.findByCourseIdInOrderByAppStatus(courseList);
    }

    @Override
    public List<Application> findBySchoolId(String schoolId) {
        return repository.findBySchoolId(schoolId);
    }

    @Override
    public Application approve(String applicationId) {
        Application application = repository.findByApplicationId(applicationId);
        application.setAppStatus(AppStatus.PASS.getCode());
        return repository.save(application);
    }

    @Override
    public Application deny(String applicationId) {
        Application application = repository.findByApplicationId(applicationId);
        application.setAppStatus(AppStatus.FAIL.getCode());
        return repository.save(application);
    }

    @Override
    public Application findByApplicationId(String applicationId) {
        return repository.findByApplicationId(applicationId);
    }

    @Override
    public Application save(Application application) {
        return repository.save(application);
    }

    @Override
    public String findSchoolName(String applicationId) {
        String schoolId = repository.findByApplicationId(applicationId).getSchoolId();
        return schoolService.findBySchoolId(schoolId).getSchoolName();
    }

    @Override
    public Date calculateEndTime(Date startTime, Integer courseId) {
        Integer period = courseService.findOne(courseId).getPeriod();
        DateTime endTime = new DateTime(startTime).plusWeeks(period);
        return endTime.toDate();
    }
}
