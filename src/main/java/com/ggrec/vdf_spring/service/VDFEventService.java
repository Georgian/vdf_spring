package com.ggrec.vdf_spring.service;

import com.ggrec.vdf_spring.domain.VDFEvent;
import com.ggrec.vdf_spring.repository.VDFEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VDFEventService {

    private VDFEventRepository vdfEventRepository;

    @Autowired
    public VDFEventService(VDFEventRepository vdfEventRepository) {
        this.vdfEventRepository = vdfEventRepository;
    }

    public void save(VDFEvent vdfEvent) {
        vdfEventRepository.save(vdfEvent);
    }

}
