package com.platform.maker.converter;

import com.platform.maker.Form.AddCourseForm;
import com.platform.maker.dataobject.Course;
import org.springframework.beans.BeanUtils;

public class AddCourseForm2CourseConverter {
    public static Course convert(AddCourseForm addCourseForm) {
        Course course = new Course();
        //BeanUtils.copyProperties(A,B);
        //     是A中的值付给B
        BeanUtils.copyProperties(addCourseForm,course);
        return course;
    }
}
