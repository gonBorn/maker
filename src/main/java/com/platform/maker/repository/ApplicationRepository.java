package com.platform.maker.repository;

import com.platform.maker.dataobject.Application;
import com.platform.maker.MultiKeyClass.ApplicationMultiKeyClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, String> {
    //通过获取机构的全部courseId来搜索全部申请
    List<Application> findByCourseIdInOrderByAppStatus(List<Integer> courseList);
    List<Application> findBySchoolId(String schoolId);
    Application findByApplicationId(String applicationId);
}
