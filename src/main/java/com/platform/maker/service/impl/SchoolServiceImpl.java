package com.platform.maker.service.impl;

import com.platform.maker.dataobject.School;
import com.platform.maker.repository.SchoolRepository;
import com.platform.maker.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository repository;
    @Override
    public School findBySchoolId(String schoolId) {
        return repository.findBySchoolId(schoolId);
    }
}
