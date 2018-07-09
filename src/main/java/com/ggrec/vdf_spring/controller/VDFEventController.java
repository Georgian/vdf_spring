package com.ggrec.vdf_spring.controller;

import com.ggrec.vdf_spring.domain.VDFEvent;
import com.ggrec.vdf_spring.service.VDFEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/event")
public class VDFEventController {

    private VDFEventService vdfEventService;

    @Autowired
    public VDFEventController(VDFEventService vdfEventService) {
        this.vdfEventService = vdfEventService;
    }

    @GetMapping
    public List<VDFEvent> getAll(
            @RequestParam(value = "discipline", required =  false) List<String> disciplines,
            @RequestParam(value = "organizer", required = false) List<String> organizers,
            @RequestParam(value = "query", required = false) String query
    ) {

        return vdfEventService.getAll(query, disciplines, organizers);
    }

    @PostMapping(path="/add")
    public void add(@RequestBody VDFEvent vdfEvent) {
        vdfEventService.save(vdfEvent);
    }

}
