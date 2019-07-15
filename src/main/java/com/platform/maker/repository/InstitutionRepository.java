package com.platform.maker.repository;

import com.platform.maker.dataobject.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institution,Integer> {
    Institution findByInstitutionId(String institutionId);
}
