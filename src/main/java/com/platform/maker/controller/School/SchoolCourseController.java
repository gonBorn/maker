package com.platform.maker.controller.School;

import com.platform.maker.VO.ResultVO;
import com.platform.maker.VO.SchoolVO.SchoolCourseTypeVO;
import com.platform.maker.VO.SchoolVO.SchoolCourseVO;
import com.platform.maker.dataobject.Course;
import com.platform.maker.dataobject.CourseSchool;
import com.platform.maker.dataobject.School;
import com.platform.maker.dataobject.StudentCourse;
import com.platform.maker.service.CourseSchoolService;
import com.platform.maker.service.CourseService;
import com.platform.maker.service.StudentCourseService;
import com.platform.maker.utils.ResultVOUtil;
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
@RequestMapping("/school/course")
public class SchoolCourseController {
    @Autowired
    private CourseSchoolService courseSchoolService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentCourseService studentCourseService;
    @GetMapping("/unselected")
    public ResultVO unselect(HttpServletRequest request){
        HttpSession session=request.getSession();
        School school=(School)session.getAttribute("school");
        String schoolId="";
        if(school!=null)schoolId=school.getSchoolId();
        else return null;
        List<List<Course>> res = new ArrayList<>();
        //找出全部创客课程
        List<Course> courseList = courseService.findAll();
        //找出学校已选课程
        List<CourseSchool> courseSchoolList =courseSchoolService.findBySchoolId(schoolId);
        //求差集得学校可选课程
        for (CourseSchool courseSchool: courseSchoolList) {
            //从“学校选课”中获取课程Id,利用courseService根据课程Id找到该课程，并从全部课程列表courseList中移除该course
            courseList.remove(courseService.findOne(courseSchool.getCourseId()));
        }
        List<Course> phy = new ArrayList<>();
        List<Course> art = new ArrayList<>();
        List<Course> eng = new ArrayList<>();
        //按课程类别存放课程进不同的课程列表
        for (Course course:courseList) {
            switch (course.getCourseType()){
                case 1:phy.add(course);break;
                case 2:art.add(course);break;
                case 3:eng.add(course);break;
            }
        }
        res.add(phy);
        res.add(art);
        res.add(eng);

        return ResultVOUtil.success(res);
    }
    @GetMapping("/selected")
    public ResultVO select(HttpServletRequest request){
        HttpSession session=request.getSession();
        School school=(School)session.getAttribute("school");
        String schoolId="";
        if(school!=null)schoolId=school.getSchoolId();
        else return null;
        //SchoolCourseTypeVO存放“进行中”或“已完结”状态下的课程
        List<SchoolCourseTypeVO> schoolCourseTypeVOS = new ArrayList<>();
        List<SchoolCourseVO> continued = new ArrayList<>();
        List<SchoolCourseVO> finished = new ArrayList<>();
        //从学校选课表中找出全部该校选过的课
        List<CourseSchool> courseSchoolList = courseSchoolService.findBySchoolId(schoolId);
        for (CourseSchool courseSchool:courseSchoolList) {
            //SchoolCourseVO是存放每个课程具体信息的视图对象
            SchoolCourseVO schoolCourseVO = new SchoolCourseVO();
            Integer courseId = courseSchool.getCourseId();
            schoolCourseVO.setCourseName(courseService.findCourseName(courseId));
            schoolCourseVO.setCourseType(courseService.findCourseTypeByCourseId(courseId));
            schoolCourseVO.setPeriod(courseService.findOne(courseId).getPeriod());
            //传入该校该创客班级的学生数量
            schoolCourseVO.setStuNum(studentCourseService.stuNumInThisClass(courseId,schoolId));
            schoolCourseVO.setStartTime(courseSchool.getStartTime());
            schoolCourseVO.setEndTime(courseSchool.getEndTime());
            if (courseSchoolService.isCourseFinished(courseId,schoolId)) {
                finished.add(schoolCourseVO);
            }
            else {
                continued.add(schoolCourseVO);
            }
        }
        //填充数据
        SchoolCourseTypeVO fin = new SchoolCourseTypeVO();
        SchoolCourseTypeVO con = new SchoolCourseTypeVO();
        con.setProcessType("进行中");
        con.setCourses(continued);
        fin.setProcessType("已完结");
        fin.setCourses(finished);
        schoolCourseTypeVOS.add(con);
        schoolCourseTypeVOS.add(fin);
        //最后向前端返回schoolCourseTypeVOS
        return ResultVOUtil.success(schoolCourseTypeVOS);
    }
}
