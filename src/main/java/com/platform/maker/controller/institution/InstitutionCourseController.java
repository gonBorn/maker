package com.platform.maker.controller.institution;

import com.platform.maker.Enum.CourseTypeEnum;
import com.platform.maker.Enum.ResultEnum;
import com.platform.maker.Form.AddCourseForm;
import com.platform.maker.VO.InsVO.Cou.InsCouListVO;
import com.platform.maker.VO.InsVO.Cou.SchoolsVO;
import com.platform.maker.VO.InsVO.course.InsCourseInfo;
import com.platform.maker.VO.InsVO.course.InsCourseVO;
import com.platform.maker.VO.ResultVO;
import com.platform.maker.converter.AddCourseForm2CourseConverter;
import com.platform.maker.dataobject.Course;
import com.platform.maker.dataobject.CourseSchool;
import com.platform.maker.dataobject.Institution;
import com.platform.maker.exception.MakerException;
import com.platform.maker.service.*;
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
@RequestMapping("/institution/course")
@Slf4j
public class InstitutionCourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseSchoolService courseSchoolService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentCourseService studentCourseService;
    @Autowired
    private InstitutionService institutionService;
    //创建课程
    @PostMapping(value="/add")
    //@Valid注解用于表单验证
    public ResultVO<Map<String,Integer>> create(@Valid AddCourseForm addCourseForm, BindingResult bindingResult, HttpServletRequest request) {
        HttpSession session=request.getSession();
        Institution institution=(Institution)session.getAttribute("institution");
        String institutionId="";
        //从session中获取institutionId
        if(institution!=null)institutionId=institution.getInstitutionId();
        else return null;
        //如果用户填写表单格式不正确则将该错误写入日志文件
        if (bindingResult.hasErrors()) {
            log.error("【创建课程】参数不正确, addCourseForm={}", addCourseForm);
            throw new MakerException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //确认机构用户正常登陆、创建课程的表单格式无误后，将表单数据转换Course实体类对象
        Course course = AddCourseForm2CourseConverter.convert(addCourseForm);
        course.setInstitutionId(institutionId);
        //将新课程对象写入数据库
        Course addResult = courseService.save(course);
        Map<String,Integer> map = new HashMap<>();
        map.put("courseId",addResult.getCourseId());
        //向前端返回新生成的课程编号
        return ResultVOUtil.success(map);
    }

    //查看课程
    @GetMapping("/list")
    public ResultVO list(HttpServletRequest request) {
        //获取session的步骤与上文相同
//        HttpSession session=request.getSession();
//        Institution institution=(Institution)session.getAttribute("institution");
//        String institutionId="";
//        if(institution!=null)institutionId=institution.getInstitutionId();
//        else return null;
        String institutionId = "123456";
        //通过institutionId在Course数据表中找到该机构下的全部创客课程
        List<Course> courseList = courseService.findByInstitutionId(institutionId);
        //insCouListVOList用于存储每个课程的insCouListVO
        List<InsCouListVO> insCouListVOList = new ArrayList<>();
        //遍历获取到的该机构下的全部课程
        for (Course course:courseList) {
            Integer courseId = course.getCourseId();
            InsCouListVO insCouListVO = new InsCouListVO();
            //先将Course的courseName\description传入
            BeanUtils.copyProperties(course,insCouListVO);
            //数据库中存储的课程类型数据为int类型，因此遍历枚举类将int转换为中文字符串
            for (CourseTypeEnum courseTypeEnum:CourseTypeEnum.values()) {
                if(courseTypeEnum.getCode()==course.getCourseType()) {
                    insCouListVO.setCourseType(courseTypeEnum.getMessage());
                }
            }
            //获取该课程报名的学校数量
            insCouListVO.setSchoolNum(institutionService.findSchoolNum(courseId));
            //获取报名的学生总人数
            insCouListVO.setStuNum(institutionService.findStuNum(courseId));
            //用schoolsVOS存储每个课程下报名学校的具体情况
            List<SchoolsVO> schoolsVOS = new ArrayList<>();
            List<CourseSchool> courseSchoolList = courseSchoolService.findByCourseId(courseId);
            for (CourseSchool courseSchool:courseSchoolList) {
                String schoolId = courseSchool.getSchoolId();
                SchoolsVO schoolsVO = new SchoolsVO();
                schoolsVO.setSchoolName(schoolService.findBySchoolId(schoolId).getSchoolName());
                schoolsVO.setStuNum(studentCourseService.stuNumInThisClass(courseId,schoolId));
                schoolsVOS.add(schoolsVO);
            }
            insCouListVO.setSchoolsVOS(schoolsVOS);
            insCouListVOList.add(insCouListVO);
        }
        return ResultVOUtil.success(insCouListVOList);
//        List<Course> physics = new ArrayList<>();
//        List<Course> art = new ArrayList<>();
//        List<Course> english = new ArrayList<>();
//        //将机构课程分类
//        for (Course course:courseList) {
//            switch (course.getCourseType()){
//                case 1:physics.add(course);break;
//                case 2:art.add(course);break;
//                case 3:english.add(course);break;
//            }
//        }
//        List<List<Course>> courses =new ArrayList<List<Course>>();
//        courses.add(physics);
//        courses.add(art);
//        courses.add(english);
//        //InsCourseVO按课程类
//        List<InsCourseVO> insCourseVOList = new ArrayList<>();
//        for(CourseTypeEnum courseTypeEnum:CourseTypeEnum.values()) {
//            InsCourseVO insCourseVO = new InsCourseVO();
//            insCourseVO.setType(courseTypeEnum.getCode());
//            insCourseVO.setTypeName(courseTypeEnum.getMessage());
//            //InsCourseInfoVO按课程
//            List<InsCourseInfo> insCourseInfos = new ArrayList<>();
//            for (Course course:courses.get(courseTypeEnum.getCode()-1)){
//                InsCourseInfo insCourseInfo = new InsCourseInfo();
//                BeanUtils.copyProperties(course,insCourseInfo);
//                //SchoolsVO存放学校选课信息
//                List<SchoolsVO> schoolsVOList = new ArrayList<>();
//                List<CourseSchool> courseSchoolList = courseSchoolService.findByCourseId(course.getCourseId());
//                insCourseInfo.setNum(courseSchoolList.size());
//                for(CourseSchool courseSchool:courseSchoolList) {
//                    SchoolsVO schoolsVO = new SchoolsVO();
//                    String schoolId = courseSchool.getSchoolId();
//                    schoolsVO.setSchoolName(schoolService.findBySchoolId(schoolId).getSchoolName());
//                    schoolsVO.setStartDate(courseSchool.getStartTime());
//                    schoolsVO.setEndDate(courseSchool.getEndTime());
//                    schoolsVO.setTeacherName(teacherService.findTeacherName(course.getCourseId(),"2151601038"));
//                    schoolsVO.setStuNum(studentCourseService.findStudents(course.getCourseId(),schoolId).size());
//                    schoolsVOList.add(schoolsVO);
//                }
//                insCourseInfo.setSchools(schoolsVOList);
//                insCourseInfos.add(insCourseInfo);
//            }
//            insCourseVO.setInsCourseInfos(insCourseInfos);
//            insCourseVOList.add(insCourseVO);
//        }
//        return ResultVOUtil.success(insCourseVOList);
    }
}