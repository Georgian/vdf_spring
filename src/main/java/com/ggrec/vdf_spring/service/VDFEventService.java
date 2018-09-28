package com.ggrec.vdf_spring.service;

import com.ggrec.vdf_spring.domain.VDFEvent;
import com.ggrec.vdf_spring.repository.VDFEventRepository;
import com.ggrec.vdf_spring.util.VDFUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void delete(long id) {
        vdfEventRepository.deleteById(id);
    }

    public VDFEvent getById(long id) {
        return vdfEventRepository.getOne(id);
    }

    public List<VDFEvent> getAll() {
        return vdfEventRepository.findAll();
    }

}
