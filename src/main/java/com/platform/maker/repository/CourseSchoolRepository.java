package com.platform.maker.repository;

import com.platform.maker.MultiKeyClass.CourseSchoolMultiKey;
import com.platform.maker.dataobject.CourseSchool;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface CourseSchoolRepository extends JpaRepository<CourseSchool, CourseSchoolMultiKey> {
    List<CourseSchool> findByCourseIdIn(List<Integer> courseList);

    /** 结束时间小于当前日期则说明课程已完结 */
    List<CourseSchool> findByEndTimeLessThanOrderByStartTime(Date now);

    List<CourseSchool> findByEndTimeGreaterThanEqualOrderByStartTime(Data now);

    List<CourseSchool> findByCourseId(Integer courseId);

    List<CourseSchool> findBySchoolId(String schoolId);

    CourseSchool findByCourseIdAndSchoolId(Integer courseId,String schoolId);

    List<CourseSchool> findByTeacherId(String teacherId);
}
