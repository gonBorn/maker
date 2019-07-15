package com.platform.maker.VO.InsVO.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class ApplicationVO {
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date leftBorder;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date rightBorder;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh-mm-ss")
    private Date createTime;
    private String schoolName;
    private String courseName;
//    private String otherRequest;
    private String appStatus;
    private String applicationId;
}
