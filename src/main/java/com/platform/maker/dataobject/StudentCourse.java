package com.platform.maker.dataobject;

import com.platform.maker.MultiKeyClass.StudentCourseMultiKey;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@IdClass(StudentCourseMultiKey.class)
public class StudentCourse {
    @Id
    private Integer courseId;
    @Id
    private String schoolId;
    @Id
    private String studentId;

    public StudentCourse() {
    }

    public StudentCourse(Integer courseId, String schoolId, String studentId) {
        this.courseId = courseId;
        this.schoolId = schoolId;
        this.studentId = studentId;
    }
}
