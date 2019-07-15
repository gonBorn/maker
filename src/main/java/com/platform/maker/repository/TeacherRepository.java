package com.platform.maker.repository;

import com.platform.maker.dataobject.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,String> {
    Teacher findByTeacherId(String teacherId);
    List<Teacher> findByInstitutionId(String institutionId);
}
