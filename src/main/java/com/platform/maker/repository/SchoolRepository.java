package com.platform.maker.repository;

import com.platform.maker.dataobject.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,String> {
    School findBySchoolId(String schoolId);
}
