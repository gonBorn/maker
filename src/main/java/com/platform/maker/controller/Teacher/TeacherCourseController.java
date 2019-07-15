package com.platform.maker.controller.Teacher;

import com.platform.maker.Enum.AppStatus;
import com.platform.maker.Enum.ResultEnum;
import com.platform.maker.Form.SubmitApplicationForm;
import com.platform.maker.Form.TeacherEvaluateForm;
import com.platform.maker.VO.ResultVO;
import com.platform.maker.VO.TeacherVO.TeaStuNameListVO;
import com.platform.maker.VO.TeacherVO.TeacherCourseVO;
import com.platform.maker.dataobject.*;
import com.platform.maker.exception.MakerException;
import com.platform.maker.service.*;
import com.platform.maker.utils.KeyUtils;
import com.platform.maker.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher/course")
@Slf4j
public class TeacherCourseController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseSchoolService courseSchoolService;
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping("/list/stuName/eva")
    public ResultVO<Map<String, String>> create(@Valid TeacherEvaluateForm teacherEvaluateForm, BindingResult bindingResult, HttpServletRequest request, @RequestParam("courseId") Integer courseId, @RequestParam("studentId") String studentId) {
//        HttpSession session = request.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("teacher");
//        String teacherId = "";
//        //如果未登录则返回登录界面
//        if (teacher != null) {
//            teacherId = teacher.getTeacherId();
//        } else return ResultVOUtil.error(403, "未登录");
        //验证评价信息是否完整，不完整则写入日志
        String teacherId = "111";
        if (bindingResult.hasErrors()) {
            log.error("【评价信息】不完整, teacherEvaluateForm={}", teacherEvaluateForm);
            throw new MakerException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //创建evaluation实体类对象存放教师提交的评价表单传递的数据
        Evaluation evaluation = new Evaluation();
        //以teacherEvaluateForm为源数据，evaluation对象为目标传值
        BeanUtils.copyProperties(teacherEvaluateForm,evaluation);
//        evaluation.setTeamwork(teacherEvaluateForm.getTeamwork());
//        evaluation.setPractical(teacherEvaluateForm.getPractical());
//        evaluation.setPositive(teacherEvaluateForm.getPositive());
//        evaluation.setCreative(teacherEvaluateForm.getCreative());
//        evaluation.setLeadership(teacherEvaluateForm.getLeadership());
        evaluation.setCourseId(courseId);
        evaluation.setStudentId(studentId);
        evaluation.setTeacherId(teacherId);
        //为该条评价生成随机码作为主键
        evaluation.setEvaluationId(KeyUtils.getUniqueKey());
        evaluation.setSchoolId(studentService.findSchoolId(studentId));
        //写入数据库
        evaluationService.save(evaluation);
        Map<String, String> map = new HashMap<>();
        //返回evaluationId
        map.put("evaluationId", evaluation.getEvaluationId());
        return ResultVOUtil.success(map);
    }

    @GetMapping("/list")
    public ResultVO checkList(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("teacher");
//        String teacherId = "";
//        if (teacher != null) {
//            teacherId = teacher.getTeacherId();
//        } else return ResultVOUtil.error(403, "未登录");
        String teacherId = "111";
        //teacherCourseVOList用于保存下面for循环中生成的每个teacherCourseVO
        List<TeacherCourseVO> teacherCourseVOList = new ArrayList<>();
        //在CourseSchool数据表中根据teacherId获取该教师的全部课程
        List<CourseSchool> teacherCourseList = courseSchoolService.teacherCourseList(teacherId);

        for (CourseSchool courseSchool : teacherCourseList) {
            Integer courseId = courseSchool.getCourseId();
            String schoolId = courseSchool.getSchoolId();
            //创建视图对象仅存储和传递前端需要的数据
            TeacherCourseVO teacherCourseVO = new TeacherCourseVO();
            teacherCourseVO.setAddress(courseSchool.getClassroomAddress());
            teacherCourseVO.setCourseName(courseService.findCourseName(courseId));
            teacherCourseVO.setSchoolId(courseSchool.getSchoolId());
            teacherCourseVO.setCourseId(courseSchool.getCourseId());
            teacherCourseVO.setSchoolName(courseSchoolService.findSchoolName(courseSchool));
            teacherCourseVO.setEndTime(courseSchool.getEndTime());
            teacherCourseVO.setStuNum(studentCourseService.stuNumInThisClass(courseId, schoolId));
            if (courseSchoolService.isCourseFinished(courseId,schoolId)) {
                teacherCourseVO.setCurrentWeeek(courseService.findOne(courseId).getPeriod());
            }
            else {
                teacherCourseVO.setCurrentWeeek(courseSchoolService.findCurrentWeek(courseId, schoolId));
            }
            teacherCourseVO.setPeriod(courseService.findOne(courseId).getPeriod());
            teacherCourseVO.setWeekday(courseSchoolService.findWeekday(courseId,schoolId));
            teacherCourseVOList.add(teacherCourseVO);
        }
        return ResultVOUtil.success(teacherCourseVOList);
    }

    @GetMapping("/list/stuName")
    public ResultVO findStuNameList(HttpServletRequest request, @RequestParam String schoolId, @RequestParam Integer courseId) {
//        HttpSession session = request.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("teacher");
//        String teacherId = "";
//        if (teacher != null) {
//            teacherId = teacher.getTeacherId();
//        } else return ResultVOUtil.error(403, "未登录");
        String teacherId = "111";
        //teaStuNameListVOList用于保存下面for循环中的每个teaStuNameListVO
        List<TeaStuNameListVO> teaStuNameListVOList = new ArrayList<>();
        List<StudentCourse> studentCourseList = studentCourseService.findStudents(courseId, schoolId);
        for (StudentCourse studentCourse : studentCourseList) {
            String studentId = studentCourse.getStudentId();
            TeaStuNameListVO vo = new TeaStuNameListVO();
            //teaStuNameListVO只需要获取学生姓名和最后评价时间
            vo.setStuName(studentService.findOne(studentCourse.getStudentId()).getStudentName());
            vo.setStudentId(studentId);
            vo.setEvaTime(evaluationService.lastEvaTime(courseId, studentId));
            teaStuNameListVOList.add(vo);
        }
        return ResultVOUtil.success(teaStuNameListVOList);
    }
}
