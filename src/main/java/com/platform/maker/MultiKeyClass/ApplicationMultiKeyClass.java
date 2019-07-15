package com.platform.maker.MultiKeyClass;

import lombok.Data;

import java.io.Serializable;
@Data
public class ApplicationMultiKeyClass implements Serializable {
    private Integer courseId;
    private String schoolId;

    public ApplicationMultiKeyClass() {
    }

    public ApplicationMultiKeyClass(Integer courseId, String schoolId) {
        this.courseId = courseId;
        this.schoolId = schoolId;
    }
    /** 重写hashcode与equals方法*/
    @Override
    public int hashCode(){
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((courseId == null) ? 0 : courseId.hashCode());
        result = PRIME * result + ((schoolId == null) ? 0 : schoolId.hashCode());
        return result;
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

        final ApplicationMultiKeyClass other = (ApplicationMultiKeyClass)obj;
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
