package com.platform.maker.controller.stu;

import com.platform.maker.Enum.CourseTypeEnum;
import com.platform.maker.VO.ResultVO;
import com.platform.maker.VO.StuVO.StuUnselectTypeVO;
import com.platform.maker.VO.StuVO.StuUnselectedCourseVO;
import com.platform.maker.dataobject.StudentCourse;
import com.platform.maker.service.CourseSchoolService;
import com.platform.maker.service.StudentCourseService;
import com.platform.maker.service.StudentService;
import com.platform.maker.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stu/course")
public class StudentUnselectCourseController {
    @Autowired
    private StudentCourseService studentCourseService;
    @Autowired
    private CourseSchoolService courseSchoolService;
    @Autowired
    private StudentService studentService;
    //先获取学校选的所有课
    //再获取该生的全部选课
    //remove
    @GetMapping("/unselected")
    public ResultVO unselectThisTypeCourse(@RequestParam("studentId") String studentId){
        List<StuUnselectTypeVO> stuUnselectTypeVOList = new ArrayList<>();
        //{学生可选课程}={学校已选课程}-{学生已选课程}
        //遍历枚举类存在的课程类型
        for (CourseTypeEnum courseTypeEnum:CourseTypeEnum.values()){
            StuUnselectTypeVO stuUnselectTypeVO = new StuUnselectTypeVO();
            stuUnselectTypeVO.setTypeId(courseTypeEnum.getCode());
            stuUnselectTypeVO.setTypeName(courseTypeEnum.getMessage());
            //调用接口获取该生可选择的该类型的课程
            List<Integer> unselectedThisTypeList = studentCourseService.findThisTypeThisStuNotSelect(courseTypeEnum.getCode(),studentId);
            //填充视图对象
            List<StuUnselectedCourseVO> stuUnselectedCourseVOList = new ArrayList<>();
            String schoolId = studentService.findSchoolId(studentId);
            for (Integer courseId:unselectedThisTypeList) {
                //StuUnselectedCourseVO用于保存前端需要的每条课程具体的信息
                StuUnselectedCourseVO stuUnselectedCourseVO = new StuUnselectedCourseVO();
                stuUnselectedCourseVO.setCourseName(studentCourseService.findCourseName(courseId));
                //需要调用CourseSchool获取上课信息
                stuUnselectedCourseVO.setStartTime(courseSchoolService.findStartTime(courseId,schoolId));
                stuUnselectedCourseVO.setEndTime(courseSchoolService.findEndTime(courseId,schoolId));
                stuUnselectedCourseVO.setTeacherName(courseSchoolService.findTeacherName(courseId,schoolId));
                stuUnselectedCourseVO.setWeekday(courseSchoolService.findWeekday(courseId,schoolId));
                stuUnselectedCourseVOList.add(stuUnselectedCourseVO);
            }
            stuUnselectTypeVO.setStuUnselectedCourseVOS(stuUnselectedCourseVOList);
            stuUnselectTypeVOList.add(stuUnselectTypeVO);
        }
       return ResultVOUtil.success(stuUnselectTypeVOList);
    }
    @PostMapping()
    public boolean selectCourse(@RequestParam String studentId,@RequestParam Integer courseId) {
        StudentCourse studentCourse = new StudentCourse();
        //StudentCourse数据表只有三个字段，对应以三个set方法，可唯一映射到一个选课记录
        String schoolId = studentService.findSchoolId(studentId);
        studentCourse.setCourseId(courseId);
        studentCourse.setSchoolId(schoolId);
        studentCourse.setStudentId(studentId);
        //将保存有学生选课信息的实体对象类的数据调用Service层接口保存进数据库
        studentCourseService.save(studentCourse);
        return true;
    }


}
