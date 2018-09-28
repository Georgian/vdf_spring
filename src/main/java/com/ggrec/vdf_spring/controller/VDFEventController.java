package com.ggrec.vdf_spring.controller;

import com.ggrec.vdf_spring.domain.VDFEvent;
import com.ggrec.vdf_spring.service.VDFEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "/event")
public class VDFEventController {

    private VDFEventService vdfEventService;

    @Autowired
    public VDFEventController(VDFEventService vdfEventService) {
        this.vdfEventService = vdfEventService;
    }

    @GetMapping
    public List<VDFEvent> getAll() {
        return vdfEventService.getAll();
    }

    @GetMapping("/{id}")
    public VDFEvent getById(@PathVariable("id") Long id) {
        return vdfEventService.getById(id);
    }

    // TODO Security reasons
//    @PostMapping
//    public void add(@RequestBody VDFEvent vdfEvent) {
//        vdfEventService.save(vdfEvent);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public void delete(@PathVariable Long id) {
//        vdfEventService.delete(id);
//    }

}
