package com.ggrec.vdf_spring.repository;

import com.ggrec.vdf_spring.domain.VDFAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VDFAthleteRepository extends JpaRepository<VDFAccount, Long>, VDFAthleteRepositoryCustom {

    VDFAccount findOneByEmail(String email);

}
