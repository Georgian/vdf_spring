package com.ggrec.vdf_spring.service;

import com.ggrec.vdf_spring.domain.VDFEvent;
import com.ggrec.vdf_spring.domain.VDFEventTag;
import com.ggrec.vdf_spring.repository.VDFEventRepository;
import com.ggrec.vdf_spring.repository.VDFEventTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VDFEventService {

    private VDFEventRepository vdfEventRepository;
    private VDFEventTagRepository vdfEventTagRepository;

    @Autowired
    public VDFEventService(VDFEventRepository vdfEventRepository, VDFEventTagRepository vdfEventTagRepository) {
        this.vdfEventRepository = vdfEventRepository;
        this.vdfEventTagRepository = vdfEventTagRepository;
    }

    public void save(VDFEvent vdfEvent) {
        vdfEventRepository.save(vdfEvent);
    }

    public void delete(long id) {
        vdfEventRepository.deleteById(id);
    }

    public VDFEvent getById(long id) {
        return vdfEventRepository.getOne(id);
    }

    public List<VDFEvent> getAll() {
        return vdfEventRepository.findAll();
    }

    public List<VDFEventTag> getAllTags() {
        return vdfEventTagRepository.findAll();
    }

}
