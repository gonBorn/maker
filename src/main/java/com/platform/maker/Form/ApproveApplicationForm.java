package com.platform.maker.Form;

import com.platform.maker.controller.institution.InstitutionApplicationController;
import com.platform.maker.dataobject.Application;
import com.platform.maker.dataobject.Institution;
import com.platform.maker.service.ApplicationService;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Future;
import java.util.Date;
@Data
public class ApproveApplicationForm {

    private String startTime;
    @NotEmpty(message = "必须选择一名授课教师")
    private String teacherId;
    @NotEmpty(message = "必须填写上课地点")
    private String classroomAddress;
}
