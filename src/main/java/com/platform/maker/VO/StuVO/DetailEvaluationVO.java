package com.platform.maker.VO.StuVO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class DetailEvaluationVO {
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date evaluationTime;
    private Integer practical;
    private Integer positive;
    private Integer teamwork;
    private Integer leadership;
    private Integer creative;
}
