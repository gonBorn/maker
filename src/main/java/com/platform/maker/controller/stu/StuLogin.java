package com.platform.maker.controller.stu;

import com.platform.maker.dataobject.Student;
import com.platform.maker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class StuLogin {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/studentLogin")
    @ResponseBody
    public boolean login(@RequestParam String studentId, HttpServletRequest request){
        System.out.println(studentId);
        Student student=studentService.findOne(studentId);
        if (student!=null) {
            HttpSession session=request.getSession();
            session.setAttribute("student",student);
            return true;
        }else return false;
    }

    @RequestMapping(value = "/getStu")
    @ResponseBody
    public Student getStudent(HttpServletRequest request){
        HttpSession session=request.getSession();
        return (Student)session.getAttribute("student");
    }
}
