package com.platform.maker.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Evaluation {
    @Id
    private String evaluationId;

    private Date evaluationTime;

    private String studentId;

    private Integer courseId;

    private Integer practical;

    private Integer positive;

    private Integer teamwork;

    private Integer leadership;

    private Integer creative;

    private String teacherId;

    private String schoolId;

    public Evaluation() {
    }

    public Evaluation(String evaluationId, Date evaluationTime, String studentId, Integer courseId, Integer practical, Integer positive, Integer teamwork, Integer leadership, Integer creative, String teacherId) {
        this.evaluationId = evaluationId;
        this.evaluationTime = evaluationTime;
        this.studentId = studentId;
        this.courseId = courseId;
        this.practical = practical;
        this.positive = positive;
        this.teamwork = teamwork;
        this.leadership = leadership;
        this.creative = creative;
        this.teacherId = teacherId;
    }
}
