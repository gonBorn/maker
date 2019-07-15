package com.platform.maker.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
public class Course {
    @Id
    @GeneratedValue
    private Integer courseId;

    private String courseName;

    private Integer courseType;

    private String description;

    /** 课程适宜人群 */
    private String crowd;

    /** 课时 */
    private Integer period;

    /** 图片url */
    private String icon;

    private String institutionId;

    private Date createTime;

//    @ManyToOne
//    @JoinColumn(name ="institution_id")
//    private Institution institution;

    public Course() {
    }
}
