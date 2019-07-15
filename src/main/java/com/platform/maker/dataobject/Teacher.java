package com.platform.maker.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Teacher {
    @Id
    private String teacherId;

    private String openId;

    private String teacherName;

    private String teacherPhone;

    /** 教师类型和课程类型使用同一个枚举 */
    private Integer teacherType;

    private String institutionId;

    public Teacher() {
    }
}
