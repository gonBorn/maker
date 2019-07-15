package com.platform.maker.MultiKeyClass;

import lombok.Data;

import java.io.Serializable;
@Data
public class CourseSchoolMultiKey implements Serializable {
    private Integer courseId;
    private String schoolId;

    public CourseSchoolMultiKey() {
    }

    public CourseSchoolMultiKey(Integer courseId, String schoolId) {
        this.courseId = courseId;
        this.schoolId = schoolId;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final CourseSchoolMultiKey other = (CourseSchoolMultiKey)obj;
        if(courseId == null){
            if(other.courseId != null){
                return false;
            }
        }else if(!courseId.equals(other.courseId)){
            return false;
        }
        if(schoolId == null){
            if(other.schoolId != null){
                return false;
            }
        }else if(!schoolId.equals(other.schoolId)){
            return false;
        }
        return true;
    }
}
