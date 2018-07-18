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

    public List<VDFEvent> getAll(String query, List<String> sports, List<String> disciplines, List<String> organizers) {

        Stream<VDFEvent> events = vdfEventRepository.findAll().stream();

        if (sports != null)
            events = events.filter(e -> sports.contains(e.getSport()));

        if (disciplines != null)
            events = events.filter(e -> disciplines.contains(e.getDiscipline()));

        if (organizers != null)
            events = events.filter(e -> organizers.contains(e.getOrganizer()));

        if (!VDFUtils.isNullOrEmpty(query))
            events = events.filter(e -> e.matchesQuery(query));

        return events.collect(Collectors.toList());
    }

}
