package com.platform.maker.controller.School;

import com.platform.maker.dataobject.School;
import com.platform.maker.dataobject.Student;
import com.platform.maker.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SchoolLogin {
    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "schoolLogin")
    @ResponseBody
    public boolean schoolLogin(@RequestParam String schoolId, HttpServletRequest request){
        System.out.println(schoolId);
        School school=schoolService.findBySchoolId(schoolId);
        if (school!=null) {
            HttpSession session=request.getSession();
            session.setAttribute("school",school);
            return true;
        }else return false;
    }
}
