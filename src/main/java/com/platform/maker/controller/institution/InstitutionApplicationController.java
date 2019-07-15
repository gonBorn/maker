package com.platform.maker.controller.institution;

import com.platform.maker.Enum.AppStatus;
import com.platform.maker.Enum.ResultEnum;
import com.platform.maker.Enum.WeekdayEnum;
import com.platform.maker.Form.ApproveApplicationForm;
import com.platform.maker.VO.InsVO.application.ApplicationVO;
import com.platform.maker.VO.ResultVO;
import com.platform.maker.dataobject.Application;
import com.platform.maker.dataobject.Course;
import com.platform.maker.dataobject.CourseSchool;
import com.platform.maker.dataobject.Institution;
import com.platform.maker.exception.MakerException;
import com.platform.maker.service.ApplicationService;
import com.platform.maker.service.CourseSchoolService;
import com.platform.maker.service.CourseService;
import com.platform.maker.service.SchoolService;
import com.platform.maker.utils.ResultVOUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/institution/application")
@Slf4j
public class InstitutionApplicationController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private CourseSchoolService courseSchoolService;
    @GetMapping("/list")
    public ResultVO list(HttpServletRequest request) {
        //获取session
//        HttpSession session=request.getSession();
//        Institution institution=(Institution)session.getAttribute("institution");
//        String institutionId="";
//        if(institution!=null)institutionId=institution.getInstitutionId();
//        else return null;
        String institutionId = "123456";
        //找到该机构全部课程列表
        List<Course> courseList = courseService.findByInstitutionId(institutionId);
        //先找到机构下开设的所有课程的id
        List<Integer> courseIdList = new ArrayList<>();
        for (Course course:courseList) {
            courseIdList.add(course.getCourseId());
        }
        //由于application中没有将institution设为外键，只能通过courseIdList代替institutionId在Application数据表中寻找此课程范围内的学校选课申请
        List<Application> applicationList = applicationService.findByCourseList(courseIdList);
        List<ApplicationVO> applicationVOList = new ArrayList<>();

        for (Application application:applicationList) {
            //筛选出申请状态为“未审核”和“拒绝”的
            if (application.getAppStatus()==0||application.getAppStatus()==2) {
                ApplicationVO applicationVO = new ApplicationVO();
                //一连串set操作为视图对象填充数据
                applicationVO.setSchoolName(applicationService.findSchoolName(application.getApplicationId()));
                applicationVO.setCourseName(courseService.findOne(application.getCourseId()).getCourseName());
                applicationVO.setCreateTime(application.getCreateTime());
                applicationVO.setLeftBorder(application.getStartTimeLeftBorder());
                applicationVO.setRightBorder(application.getStartTimeRightBorder());
                applicationVO.setApplicationId(application.getApplicationId());
                //遍历AppStatus这个枚举类，将数据库的int类型的申请状态转换为字符串
                for (AppStatus appStatus:AppStatus.values()){
                    if (appStatus.getCode()==application.getAppStatus()){
                        applicationVO.setAppStatus(appStatus.getMessage());
                    }
                }
                applicationVOList.add(applicationVO);
            }

        }
        return ResultVOUtil.success(applicationVOList);
    }

    @PostMapping("/details")
    public ResultVO approve(@Valid ApproveApplicationForm approveApplicationForm, BindingResult bindingResult,@RequestParam("applicationId") String applicationId) throws ParseException {
        //除了注解进行的表单验证，还要判断机构设置的开课时间是否在学校提出申请中的，最早开始时间和最晚开始时间之间
        Application application = applicationService.findByApplicationId(applicationId);
        //学校申请的最早开始时间
        Date startTimeLeftBorder =  application.getStartTimeLeftBorder();
        //学校申请的最晚开始时间
        Date startTimeRightBorder = application.getStartTimeRightBorder();
        Interval interval = new Interval(new DateTime(startTimeLeftBorder),new DateTime(startTimeRightBorder));
        boolean isContained = interval.contains(new DateTime(approveApplicationForm.getStartTime()));
        if (bindingResult.hasErrors()||!isContained) {
            log.error("【审批课程】参数不正确, approveApplicationForm={}", approveApplicationForm);
            throw new MakerException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=format.parse(approveApplicationForm.getStartTime());
        //更改申请的状态，由审核中变为通过
        applicationService.approve(applicationId);
        //在学校选课表中添加新的数据
        CourseSchool courseSchool = new CourseSchool();
        Integer courseId = application.getCourseId();
        courseSchool.setCourseId(courseId);
        courseSchool.setSchoolId(application.getSchoolId());
        courseSchool.setTeacherId(approveApplicationForm.getTeacherId());
        courseSchool.setStartTime(startDate);

        courseSchool.setClassroomAddress(approveApplicationForm.getClassroomAddress());
        //DateTime计算结束时间（开始时间+课时）再转换为Date类型
        Date endTime = applicationService.calculateEndTime(startDate,courseId);
        courseSchool.setEndTime(endTime);
        //利用JodaTime获取开始时间的星期
        String weekday = new LocalDate(startDate).dayOfWeek().getAsShortText(Locale.CHINA);
        int day = 0;
        for (WeekdayEnum weekdayEnum:WeekdayEnum.values()) {
            if (weekdayEnum.getMessage()==weekday) {
                day=weekdayEnum.getCode();
            }
        }
        courseSchool.setWeekday(day);
        courseSchoolService.save(courseSchool);
        return ResultVOUtil.success();
    }

    @PostMapping
    public ResultVO deny(@RequestParam("applicationId") String applicationId) {
        //拒绝该申请只需修改该申请的状态
        applicationService.deny(applicationId);
        return ResultVOUtil.success(applicationId);
    }
}
