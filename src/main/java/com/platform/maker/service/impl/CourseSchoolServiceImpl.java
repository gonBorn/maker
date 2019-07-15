package com.platform.maker.service.impl;

import com.platform.maker.Enum.WeekdayEnum;
import com.platform.maker.dataobject.Course;
import com.platform.maker.dataobject.CourseSchool;
import com.platform.maker.dataobject.Student;
import com.platform.maker.dataobject.StudentCourse;
import com.platform.maker.repository.CourseSchoolRepository;
import com.platform.maker.service.*;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseSchoolServiceImpl implements CourseSchoolService {
    @Autowired
    private CourseSchoolRepository repository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SchoolService schoolService;

    @Override
    public List<CourseSchool> findByCourseIdIn(List<Integer> courseList) {
        return repository.findByCourseIdIn(courseList);
    }

    @Override
    public List<CourseSchool> findByEndTimeLessThanOrderByStartTime(Date now) {
        return repository.findByEndTimeLessThanOrderByStartTime(now);
    }

    @Override
    public List<CourseSchool> findByEndTimeGreaterThanEqualOrderByStartTime(Data now) {
        return repository.findByEndTimeGreaterThanEqualOrderByStartTime(now);
    }

    @Override
    public CourseSchool findOne(Integer courseId, String schoolId) {
        return repository.findByCourseIdAndSchoolId(courseId, schoolId);
    }

    @Override
    public List<CourseSchool> findBySchoolId(String schoolId) {
        return repository.findBySchoolId(schoolId);
    }

    @Override
    public List<CourseSchool> findByCourseId(Integer courseId) {
        return repository.findByCourseId(courseId);
    }

    @Override
    public CourseSchool save(CourseSchool courseSchool) {
        return repository.save(courseSchool);
    }

    @Override
    public String findClassAddress(Integer courseId, String studentId) {
        String schoolId = studentService.findOne(studentId).getSchoolId();
        return repository.findByCourseIdAndSchoolId(courseId, schoolId).getClassroomAddress();
    }

    @Override
    public String findWeekday(Integer courseId, String schoolId) {
        Integer weekday = repository.findByCourseIdAndSchoolId(courseId, schoolId).getWeekday();
        String day = "";
        for (WeekdayEnum weekdayEnum : WeekdayEnum.values()) {
            if (weekday.equals(weekdayEnum.getCode())) {
                day = weekdayEnum.getMessage();
            }
        }
        return day;
    }

    @Override
    public Integer findCurrentWeek(Integer courseId, String schoolId) {
        Date startTime = repository.findByCourseIdAndSchoolId(courseId, schoolId).getStartTime();
        return (Days.daysBetween(new LocalDate(startTime), LocalDate.now()).getDays() / 7 + 1);
    }

    @Override
    public List<Integer> findThisTypeThisSchoolSelect(Integer typeId, String schoolId) {
        List<Course> courseList = courseService.findByCourseType(typeId);
        //先获取该类型的全部课程ID
        List<Integer> courseIdList = new ArrayList<>();
        for (Course course : courseList) {
            courseIdList.add(course.getCourseId());
        }
        //再获取全校的课程ID
        List<CourseSchool> coursesSchoolSelected = repository.findBySchoolId(schoolId);
        List<Integer> courseSchoolIdList = new ArrayList<>();
        for (CourseSchool courseSchool : coursesSchoolSelected) {
            courseSchoolIdList.add(courseSchool.getCourseId());
        }
        //获取既存在于该类型全部课程ID中，又存在于学校申请过的课程ID中的课程ID，即学校选择的该类型的课程ID
        List<Integer> thisTypeCourseSchoolSelected = new ArrayList<>();
        for (Integer courseId : courseSchoolIdList) {
            if (courseIdList.contains(courseId)) {
                thisTypeCourseSchoolSelected.add(courseId);
            }
        }
        return thisTypeCourseSchoolSelected;
    }

    @Override
    public Date findStartTime(Integer courseId, String schoolId) {
        return repository.findByCourseIdAndSchoolId(courseId, schoolId).getStartTime();
    }

    @Override
    public Date findEndTime(Integer courseId, String schoolId) {
        return repository.findByCourseIdAndSchoolId(courseId, schoolId).getEndTime();
    }

    @Override
    public String findTeacherName(Integer courseId, String schoolId) {
        CourseSchool courseSchool = repository.findByCourseIdAndSchoolId(courseId, schoolId);
        String teacherId = courseSchool.getTeacherId();
        return teacherService.findTeacherName(teacherId);
    }

    @Override
    public Boolean isCourseFinished(Integer courseId, String schoolId) {
        return new DateTime(repository.findByCourseIdAndSchoolId(courseId, schoolId).getEndTime()).isBeforeNow();
    }

    @Override
    public List<CourseSchool> teacherCourseList(String teacherId) {
        return repository.findByTeacherId(teacherId);
    }

    @Override
    public String findSchoolName(CourseSchool courseSchool) {
        String schoolId = courseSchool.getSchoolId();
        return schoolService.findBySchoolId(schoolId).getSchoolName();
    }
}
