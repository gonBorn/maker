package com.platform.maker.repository;

import com.platform.maker.dataobject.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findByStudentId(String studentId);
}
