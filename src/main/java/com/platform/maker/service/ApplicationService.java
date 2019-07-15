package com.platform.maker.service;

import com.platform.maker.dataobject.Application;

import java.util.Date;
import java.util.List;

public interface ApplicationService {
    /** 创建申请 */


    /** 机构查看申请 */
    List<Application> findByCourseList(List<Integer> courseList);
    /** 学校查看申请 */
    List<Application> findBySchoolId(String schoolId);
    /** 审批 */
    Application approve(String applicationId);
    /** 否决*/
    Application deny(String applicationId);

    Application findByApplicationId(String applicationId);

    Application save(Application application);
    //发出申请的学校名
    String findSchoolName(String applicationId);

    //计算结课时间
    Date calculateEndTime(Date startTime,Integer courseId);

}
