package com.platform.maker.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Lesson {
    @Id
    @GeneratedValue
    private Integer lessonId;

    private String activityName;

    private String activityGoal;

    private Integer courseId;

    public Lesson() {
    }
}
