package com.platform.maker.controller.stu;

import com.platform.maker.Enum.CourseStatus;
import com.platform.maker.Enum.WeekdayEnum;
import com.platform.maker.VO.ResultVO;
import com.platform.maker.VO.StuVO.DetailEvaluationVO;
import com.platform.maker.VO.StuVO.EvaluationVO;
import com.platform.maker.VO.StuVO.StuCourseInfoVO;
import com.platform.maker.VO.StuVO.StuCourseVO;
import com.platform.maker.dataobject.Evaluation;
import com.platform.maker.dataobject.Student;
import com.platform.maker.dataobject.StudentCourse;
import com.platform.maker.service.*;
import com.platform.maker.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stu/course")
@Slf4j
public class StudentCourseSelectedController {

    @Autowired
    private CourseSchoolService courseSchoolService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/selected")
    //@RequestParam("studentId") String studentId
    public ResultVO selected(@RequestParam("studentId") String studentId) {

        //stuCourseVO.setStuCourseInfoVOList(Arrays.asList(stuCourseInfoVO));
        //resultVO.setData(Arrays.asList(stuCourseVO));
        //数据拼装
        //根据学号获取该生选课的课程id
        List<StudentCourse> courseList = studentCourseService.findByStudentId(studentId);
        ArrayList<Integer> continueCourseIdList = new ArrayList<>();
        ArrayList<Integer> finishCourseIdList = new ArrayList<>();
        //将进行中和已完结的课程分开保存在不同的列表
        for (StudentCourse sc : courseList) {
            Integer currentCourseId = sc.getCourseId();
            if (studentCourseService.isCourseFinished(currentCourseId, studentId)) {
                finishCourseIdList.add(currentCourseId);
            } else {
                continueCourseIdList.add(currentCourseId);
            }
        }
        //将已划分好的进行中课程和已完结课程列表保存到一个列表中，便于下面使用for循环
        List<List<Integer>> courseIdListDividedByCourseStatus = new ArrayList<>();
        courseIdListDividedByCourseStatus.add(continueCourseIdList);
        courseIdListDividedByCourseStatus.add(finishCourseIdList);
        List<StuCourseVO> stuCourseVOList = new ArrayList<>();
//        StuCourseVO stuCourseVO1 = new StuCourseVO();
//        stuCourseVO1.setType(0);
//        stuCourseVO1.setTypeName(CourseStatus.CONTONUE.getMessage());
//
//        List<StuCourseInfoVO> stuCourseInfoVOList1 = new ArrayList<>();
        //下面的for循环执行现阶段只执行两次，第一次找进行中的课程，第二次找已完结的课程
        for (CourseStatus courseStatus : CourseStatus.values()) {
            //StuCourseVO是分别存放不同进度类型的课程，遍历枚举类得到所有课程进度类型
            List<StuCourseInfoVO> stuCourseInfoVOList = new ArrayList<>();
            StuCourseVO stuCourseVO = new StuCourseVO();
            stuCourseVO.setType(courseStatus.getCode());
            stuCourseVO.setTypeName(courseStatus.getMessage());

            //遍历courseIdListDividedByCourseStatus中的每个课程，获取所有该 生选过课程的详细信息
            for (Integer courseId : courseIdListDividedByCourseStatus.get(courseStatus.getCode())) {
                String schoolId = studentService.findSchoolId(studentId);
                //StuCourseInfoVO是用于存放具体课程信息的视图对象类
                StuCourseInfoVO stuCourseInfoVO = new StuCourseInfoVO();
                stuCourseInfoVO.setCourseId(courseId);
                stuCourseInfoVO.setTeacherName(teacherService.findTeacherName(courseId, studentId));
                stuCourseInfoVO.setCourseName(courseService.findOne(courseId).getCourseName());
                stuCourseInfoVO.setIcon(courseService.findOne(courseId).getIcon());
                stuCourseInfoVO.setAddress(courseSchoolService.findClassAddress(courseId, studentId));
                stuCourseInfoVO.setDay(courseSchoolService.findWeekday(courseId, schoolId));
                //调用Service层定义的计算当前课时的方法，利用第三方类库JodaTime
                stuCourseInfoVO.setCurrentWeek(courseSchoolService.findCurrentWeek(courseId, studentService.findSchoolId(studentId)));
                //如果当前的课程为已完结状态，则当前课时=总课时
                if (courseStatus.getCode() == 1) {
                    stuCourseInfoVO.setCurrentWeek(courseService.findOne(courseId).getPeriod());
                }
                stuCourseInfoVO.setTotalWeek(courseService.findOne(courseId).getPeriod());
                stuCourseInfoVOList.add(stuCourseInfoVO);
            }
            stuCourseVO.setStuCourseInfoVOList(stuCourseInfoVOList);
            stuCourseVOList.add(stuCourseVO);
        }
        return ResultVOUtil.success(stuCourseVOList);
    }

    @GetMapping("/selected/eva")
    //@RequestParam("studentId") String studentId,@RequestParam("courseId") Integer courseId
    public ResultVO checkEva(HttpServletRequest request, @RequestParam("courseId") Integer courseId) {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        String studentId="";
        if(student!=null)studentId=student.getStudentId();
        else return ResultVOUtil.error(403, "未登录");
//        String studentId = "2151601038";
//        Integer courseId = 1;
        //获取全班平均
        String courseName = courseService.findOne(courseId).getCourseName();
        DetailEvaluationVO classAverage = new DetailEvaluationVO();
        String schoolId = studentService.findSchoolId(studentId);
        classAverage.setCreative(evaluationService.classAverageCreative(courseId, schoolId));
        classAverage.setLeadership(evaluationService.classAverageLeadership(courseId, schoolId));
        classAverage.setPositive(evaluationService.classAveragePositive(courseId, schoolId));
        classAverage.setPractical(evaluationService.classAveragePractical(courseId, schoolId));
        classAverage.setTeamwork(evaluationService.classAverageTeamwork(courseId, schoolId));
        //获取个人平均
        DetailEvaluationVO myAverage = new DetailEvaluationVO();
        List<Integer> averageItem = evaluationService.myAverage(courseId, studentId);
        myAverage.setPractical(averageItem.get(0));
        myAverage.setPositive(averageItem.get(1));
        myAverage.setTeamwork(averageItem.get(2));
        myAverage.setLeadership(averageItem.get(3));
        myAverage.setCreative(averageItem.get(4));
        //获取个人全部评价
        List<DetailEvaluationVO> detailEvaluationVOList = new ArrayList<>();
//        DetailEvaluationVO detailEvaluationVO1 = new DetailEvaluationVO();
//        DetailEvaluationVO detailEvaluationVO2 = new DetailEvaluationVO();
        List<Evaluation> evaluationList = evaluationService.findOneStuEvaluation(courseId, studentId);
        for (Evaluation evaluation : evaluationList) {
            DetailEvaluationVO detailEvaluationVO = new DetailEvaluationVO();
            BeanUtils.copyProperties(evaluation, detailEvaluationVO);
            detailEvaluationVOList.add(detailEvaluationVO);
        }
        EvaluationVO evaluationVO = new EvaluationVO();
        evaluationVO.setCourseName(courseName);
        evaluationVO.setMyAverage(myAverage);
        evaluationVO.setClassAverage(classAverage);
        evaluationVO.setAllMyEvaluation(detailEvaluationVOList);

        return ResultVOUtil.success(evaluationVO);
    }


}
