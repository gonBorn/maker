package com.platform.maker.repository;

import com.platform.maker.dataobject.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findByCourseType(Integer courseType);
    Page<Course> findByInstitutionId(String institutionId, Pageable pageable);
    List<Course> findByInstitutionId(String institutionId);
    List<Course> findByCourseIdIn(List<Integer> courseList);
    Course findByCourseId(Integer courseId);
}
