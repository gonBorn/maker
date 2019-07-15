package com.platform.maker.controller.Teacher;

import com.platform.maker.dataobject.Student;
import com.platform.maker.dataobject.Teacher;
import com.platform.maker.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TeacherLogin {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/teacherLogin")
    @ResponseBody
    public boolean teacherLogin(@RequestParam String teacherId, HttpServletRequest request){
        Teacher teacher=teacherService.findOne(teacherId);
        if (teacher!=null) {
            HttpSession session=request.getSession();
            session.setAttribute("teacher",teacher);
            return true;
        }else return false;

    }
}
