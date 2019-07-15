package com.platform.maker.controller.institution;

import com.platform.maker.dataobject.Institution;
import com.platform.maker.dataobject.Teacher;
import com.platform.maker.service.InstitutionService;
import com.platform.maker.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class InsLogin {
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/insLogin")
    @ResponseBody
    public boolean insLogin(@RequestParam  String insId, HttpServletRequest request){
        System.out.println(insId);
        Institution institution = institutionService.findByInstitutionId(insId);
        System.out.println(institution);
        if (institution!=null) {
            HttpSession session=request.getSession();
            session.setAttribute("institution",institution);
            return true;
        }else return false;
    }

    @RequestMapping(value = "/teachers")
    @ResponseBody
    public List<Teacher> getTeachers(HttpServletRequest request){
//        HttpSession session=request.getSession();
//        Institution institution=(Institution)session.getAttribute("institution");
        String institutionId = "123456";
        //List<Teacher> teacherList = teacherService.findByInstitutionId(institution.getInstitutionId());
        List<Teacher> teacherList = teacherService.findByInstitutionId(institutionId);
        return teacherList;
    }
}
