package com.platform.maker.dataobject;

import com.platform.maker.MultiKeyClass.CourseSchoolMultiKey;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;
@Entity
@Data
@IdClass(CourseSchoolMultiKey.class)
public class CourseSchool {
    @Id
    private Integer courseId;
    @Id
    private String schoolId;

    private String classroomAddress;

    private String teacherId;

    private Date startTime;

    private Date endTime;

    private Integer weekday;

    public CourseSchool() {
    }

    public CourseSchool(Integer courseId, String schoolId, String classroomAddress, String teacherId, Date startTime, Date endTime) {
        this.courseId = courseId;
        this.schoolId = schoolId;
        this.classroomAddress = classroomAddress;
        this.teacherId = teacherId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
