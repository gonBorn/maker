package com.platform.maker.Form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class TeacherEvaluateForm {
    @NotNull(message = "缺少对positive指标的评价")
    private Integer positive;
    @NotNull(message = "缺少对practical指标的评价")
    private Integer practical;
    @NotNull(message = "缺少对teamwork指标的评价")
    private Integer teamwork;
    @NotNull(message = "缺少对creative指标的评价")
    private Integer creative;
    @NotNull(message = "缺少对leadership指标的评价")
    private Integer leadership;
}
