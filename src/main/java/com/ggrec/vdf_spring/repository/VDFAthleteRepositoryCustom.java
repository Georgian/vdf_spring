package com.ggrec.vdf_spring.repository;

import com.ggrec.vdf_spring.domain.VDFAccount;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VDFAthleteRepositoryCustom {

    Optional<VDFAccount> findByToken(String token);

}
