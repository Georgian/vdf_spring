package com.ggrec.vdf_spring.controller;

import com.ggrec.vdf_spring.domain.VDFAthlete;
import com.ggrec.vdf_spring.domain.VDFEvent;
import com.ggrec.vdf_spring.repository.VDFAthleteRepository;
import com.ggrec.vdf_spring.service.VDFEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/api/event")
public class VDFEventController {

    private VDFEventService vdfEventService;

    @Autowired
    public VDFEventController(VDFEventService vdfEventService) {
        this.vdfEventService = vdfEventService;
    }

    @GetMapping(path="/test")
    public List<VDFAthlete> getAll(HttpServletRequest httpServletRequest) {
        return null;
    }

    @PostMapping(path="/add")
    public void add(@RequestBody VDFEvent vdfEvent) {
        vdfEventService.save(vdfEvent);
    }

}
