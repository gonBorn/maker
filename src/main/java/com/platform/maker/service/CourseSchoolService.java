package com.platform.maker.service;

import com.platform.maker.dataobject.CourseSchool;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface CourseSchoolService {
    List<CourseSchool> findByCourseIdIn(List<Integer> courseList);
    List<CourseSchool> findByEndTimeLessThanOrderByStartTime(Date now);
    List<CourseSchool> findByEndTimeGreaterThanEqualOrderByStartTime(Data now);
    CourseSchool findOne(Integer courseId,String schoolId);
    List<CourseSchool> findBySchoolId(String schoolId);
    List<CourseSchool> findByCourseId(Integer courseId);
    //写入数据库
    CourseSchool save(CourseSchool courseSchool);
    //查找上课地点
    String findClassAddress(Integer courseId,String studentId);
    //查找星期几上课
    String findWeekday(Integer courseId,String schoolId);
    //查找当前课程进行到第几周
    Integer findCurrentWeek(Integer courseId,String studentId);
    //本校所有此类型的课
    List<Integer> findThisTypeThisSchoolSelect(Integer typeId,String schoolId);
    //找开课日期
    Date findStartTime(Integer courseId, String schoolId);
    //找结课日期
    Date findEndTime(Integer courseId, String schoolId);
    //找教师名字
    String findTeacherName(Integer courseId, String schoolId);
    //判断课程是否已完结
    Boolean isCourseFinished (Integer courseId,String schoolId);
    //查找教师授课列表
    List<CourseSchool> teacherCourseList(String teacherId);
    //找到学校名
    String findSchoolName(CourseSchool courseSchool);
}
