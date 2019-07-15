package com.platform.maker.service;

import com.platform.maker.dataobject.School;

public interface SchoolService {
    School findBySchoolId(String schoolId);
}
