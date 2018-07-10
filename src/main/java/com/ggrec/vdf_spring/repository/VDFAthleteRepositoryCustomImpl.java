package com.ggrec.vdf_spring.repository;

import com.ggrec.vdf_spring.domain.VDFAccount;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class VDFAthleteRepositoryCustomImpl implements VDFAthleteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<VDFAccount> findByToken(String token) {
        // TODO
        return Optional.empty();
    }

}
