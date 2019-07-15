package com.platform.maker.service.impl;

import com.platform.maker.dataobject.CourseSchool;
import com.platform.maker.dataobject.StudentCourse;
import com.platform.maker.repository.StudentCourseRepository;
import com.platform.maker.service.CourseSchoolService;
import com.platform.maker.service.CourseService;
import com.platform.maker.service.StudentCourseService;
import com.platform.maker.service.StudentService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseRepository repository;
    @Autowired
    private CourseSchoolService courseSchoolService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
//    @Override
//    public List<StudentCourse> findByStudentId(String studentId) {
//        return repository.findByStudentId(studentId);
//    }

    @Override
    public List<StudentCourse> findStudents(Integer courseId, String schoolId) {
        return repository.findByCourseIdAndSchoolId(courseId,schoolId);
    }

    @Override
    public Integer stuNumInThisClass(Integer courseId, String schoolId) {
        return repository.findByCourseIdAndSchoolId(courseId,schoolId).size();
    }

    @Override
    public List<StudentCourse> findStuCourseSelection(String schoolId, String studentId) {
        return repository.findBySchoolIdAndStudentId(schoolId,studentId);
    }

    @Override
    public List<StudentCourse> findByStudentId(String studentId) {
        return repository.findByStudentId(studentId);
    }

    @Override
    public Boolean isCourseFinished(Integer courseId, String studentId) {
        String schoolId = studentService.findOne(studentId).getSchoolId();
        return new DateTime(courseSchoolService.findOne(courseId,schoolId).getEndTime()).isBeforeNow();
    }

    @Override
    public List<Integer> findThisTypeThisStuNotSelect(Integer typeId, String studentId) {
        String schoolId = studentService.findOne(studentId).getSchoolId();
        List<Integer> thisTypeCourseSchoolSelected = courseSchoolService.findThisTypeThisSchoolSelect(typeId,schoolId);
        //获取该生选过的课程ID
        List<StudentCourse> studentCourseList = repository.findByStudentId(studentId);
        //从学校已选的该类型的课程ID移除该生已选过的课程，就是最终需要得到的该生可选的该类型的课程
        for (StudentCourse studentCourse:studentCourseList) {
            if (thisTypeCourseSchoolSelected.contains(studentCourse.getCourseId())) {
                thisTypeCourseSchoolSelected.remove(studentCourse.getCourseId());
            }
        }
        return thisTypeCourseSchoolSelected;
    }

    @Override
    public String findCourseName(Integer courseId) {
        return courseService.findCourseName(courseId);
    }

    @Override
    public StudentCourse save(StudentCourse studentCourse) {
        return repository.save(studentCourse);
    }
}
