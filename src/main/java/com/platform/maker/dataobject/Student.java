package com.platform.maker.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Student {
    @Id
    private String studentId;

    private String openId;

    private String studentName;

    private Integer grade;

    private String schoolId;

    public Student() {
    }
}
