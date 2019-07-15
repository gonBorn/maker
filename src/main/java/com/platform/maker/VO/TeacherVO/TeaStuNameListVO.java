package com.platform.maker.VO.TeacherVO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TeaStuNameListVO {
    private String studentId;
    private String stuName;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
    private Date evaTime;
}
