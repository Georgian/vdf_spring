package com.ggrec.vdf_spring.service;

import com.ggrec.vdf_spring.domain.VDFEvent;
import com.ggrec.vdf_spring.domain.VDFEventTag;
import com.ggrec.vdf_spring.repository.VDFEventRepository;
import com.ggrec.vdf_spring.repository.VDFEventTagRepository;
import com.ggrec.vdf_spring.util.VDFUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VDFEventService {

    private VDFEventRepository vdfEventRepository;

    private VDFEventTagRepository vdfEventTagRepository;

    @Autowired
    public VDFEventService(VDFEventRepository vdfEventRepository, VDFEventTagRepository vdfEventTagRepository) {
        this.vdfEventRepository = vdfEventRepository;
        this.vdfEventTagRepository = vdfEventTagRepository;
    }

    public VDFEvent save(VDFEvent vdfEvent) {
        return vdfEventRepository.save(vdfEvent);
    }

    public void delete(long id) {
        vdfEventRepository.deleteById(id);
    }

    public Optional<VDFEvent> update(long id, VDFEvent event) {
        return getById(id).map(e -> save(event));
    }

    public Optional<VDFEvent> getById(long id) {
        return Optional.ofNullable(vdfEventRepository.getOne(id));
    }

    public List<VDFEventTag> getAllTags() {
        return vdfEventTagRepository.findAll();
    }

    public List<VDFEvent> getAll() {
        return vdfEventRepository.findAll();
    }

}
