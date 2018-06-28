package com.ggrec.vdf_spring.repository;

import com.ggrec.vdf_spring.domain.VDFEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VDFEventRepository extends JpaRepository<VDFEvent, Long> {


}
