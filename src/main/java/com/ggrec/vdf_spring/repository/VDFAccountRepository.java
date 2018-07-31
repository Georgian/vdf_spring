package com.ggrec.vdf_spring.repository;

import com.ggrec.vdf_spring.domain.VDFAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VDFAccountRepository extends JpaRepository<VDFAccount, Long>, VDFAccountRepositoryCustom {

    VDFAccount findOneByEmail(String email);

    VDFAccount findOneByFacebookId(String facebookId);

}
