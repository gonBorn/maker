package com.platform.maker.converter;

import com.platform.maker.Form.AddCourseForm;
import com.platform.maker.Form.ApproveApplicationForm;
import com.platform.maker.dataobject.Application;
import com.platform.maker.dataobject.Course;
import org.springframework.beans.BeanUtils;

public class ApproveApplicationForm2ApplicationConverter {
    public static Application convert(ApproveApplicationForm approveApplicationForm) {
        Application application = new Application();
        //BeanUtils.copyProperties(A,B);
        //     是A中的值付给B
        BeanUtils.copyProperties(approveApplicationForm,application);
        return application;
    }
}
