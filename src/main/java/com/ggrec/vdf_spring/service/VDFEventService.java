package com.ggrec.vdf_spring.service;

import com.ggrec.vdf_spring.domain.VDFEvent;
import com.ggrec.vdf_spring.repository.VDFEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public List<VDFEvent> getAll(List<String> disciplines, List<String> organizers) {

        Stream<VDFEvent> events = vdfEventRepository.findAll().stream();

        if (disciplines != null)
            events = events.filter(e -> disciplines.contains(e.getDiscipline()));

        if (organizers != null)
            events = events.filter(e -> organizers.contains(e.getOrganizer()));

        return events.collect(Collectors.toList());
    }

}
