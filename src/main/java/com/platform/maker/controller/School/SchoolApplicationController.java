package com.platform.maker.controller.School;

import com.platform.maker.Enum.AppStatus;
import com.platform.maker.Enum.ResultEnum;
import com.platform.maker.Form.AddCourseForm;
import com.platform.maker.Form.ApproveApplicationForm;
import com.platform.maker.Form.SubmitApplicationForm;
import com.platform.maker.VO.ResultVO;
import com.platform.maker.converter.AddCourseForm2CourseConverter;
import com.platform.maker.converter.ApproveApplicationForm2ApplicationConverter;
import com.platform.maker.dataobject.Application;
import com.platform.maker.dataobject.Course;
import com.platform.maker.dataobject.School;
import com.platform.maker.exception.MakerException;
import com.platform.maker.service.ApplicationService;
import com.platform.maker.utils.KeyUtils;
import com.platform.maker.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/school/application")
@Slf4j
public class SchoolApplicationController {
    @Autowired
    private ApplicationService applicationService;
    @PostMapping("/add")
    public ResultVO<Map<String,String>> create(@Valid SubmitApplicationForm submitApplicationForm,@RequestParam("courseId") Integer courseId, HttpServletRequest request) throws ParseException {
        HttpSession session=request.getSession();
        School school=(School)session.getAttribute("school");
        String schoolId="";
        if(school!=null)schoolId=school.getSchoolId();
        else return null;
        Application application = new Application();
        //将表单中获取的时间数据格式化
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date startTimeLeftBorder = format.parse(submitApplicationForm.getStartTimeLeftBorder());
        Date startTimeRightBorder = format.parse(submitApplicationForm.getStartTimeRightBorder());
        //将申请课程中的表单数据传给application实体类对象
        BeanUtils.copyProperties(application,submitApplicationForm);
        //为applicationn传递其他必须的参数
        application.setCourseId(courseId);
        application.setSchoolId(schoolId);
        application.setAppStatus(AppStatus.WAIT.getCode());
        application.setApplicationId(KeyUtils.getUniqueKey());
        application.setStartTimeLeftBorder(startTimeLeftBorder);
        application.setStartTimeRightBorder(startTimeRightBorder);
        application.setOtherRequest(submitApplicationForm.getOtherRequest());
        //将申请保存进数据库
        applicationService.save(application);
        Map<String,String> map = new HashMap<>();
        //向前端返回申请编号
        map.put("applicationId",application.getApplicationId());
        return ResultVOUtil.success(map);
    }
}
