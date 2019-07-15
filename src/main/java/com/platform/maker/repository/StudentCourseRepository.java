package com.platform.maker.repository;

import com.platform.maker.MultiKeyClass.StudentCourseMultiKey;
import com.platform.maker.dataobject.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseMultiKey> {
    List<StudentCourse> findByStudentId(String studentId);
    List<StudentCourse> findByCourseIdAndSchoolId(Integer courseId, String schoolId);
    List<StudentCourse> findBySchoolIdAndStudentId(String schoolId,String studentId);
}
