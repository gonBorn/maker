package com.platform.maker.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class School {
    @Id
    private String schoolId;

    private String openId;

    private String schoolName;

    private String schoolAddress;

    private String schoolPhone;

    private Integer schoolType;

    public School() {
    }
}
