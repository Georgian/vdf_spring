package com.ggrec.vdf_spring.repository;

import com.ggrec.vdf_spring.domain.VDFEventTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VDFEventTagRepository extends JpaRepository<VDFEventTag, Long> {
}
