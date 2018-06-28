package com.ggrec.vdf_spring.repository;

import com.ggrec.vdf_spring.domain.VDFAthlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VDFAthleteRepository extends JpaRepository<VDFAthlete, Long> {

    VDFAthlete findOneByEmail(String email);

}
