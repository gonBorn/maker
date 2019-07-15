package com.platform.maker.service;

import com.platform.maker.dataobject.Institution;

public interface InstitutionService {
    Institution findByInstitutionId(String institutionId);

    Integer findSchoolNum( Integer courseId);

    Integer findStuNum(Integer courseId);

}
