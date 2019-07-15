package com.platform.maker.Form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Data
public class AddCourseForm {

    private String institutionId;
    @NotEmpty(message = "课程名必填")
    private String courseName;

    private Integer courseType;
    @NotEmpty(message = "课程简介必填")
    private String description;
    @NotEmpty(message = "适宜人群必选")
    private String crowd;
    //规定课时为不小于0，不大于100的数字
    @DecimalMax("100")
    @DecimalMin("0")
    private Integer period;
}
