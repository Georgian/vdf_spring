package com.ggrec.vdf_spring.controller;

import com.ggrec.vdf_spring.domain.VDFEvent;
import com.ggrec.vdf_spring.domain.VDFEventTag;
import com.ggrec.vdf_spring.service.VDFEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(path = "/event")
public class VDFEventController {

    private VDFEventService vdfEventService;

    @Autowired
    public VDFEventController(VDFEventService vdfEventService) {
        this.vdfEventService = vdfEventService;
    }

    @PermitAll
    @GetMapping
    public List<VDFEvent> getAll() {
        return vdfEventService.getAll();
    }

    @PermitAll
    @GetMapping("/tags")
    public List<VDFEventTag> getAllTags() { return vdfEventService.getAllTags(); }

    @GetMapping("/{id}")
    public VDFEvent getById(@PathVariable("id") Long id) {
        return vdfEventService.getById(id);
    }

    @RolesAllowed("ADMIN")
    @PostMapping
    public void add(@RequestBody VDFEvent vdfEvent) {
        vdfEventService.save(vdfEvent);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/{id}")
    public void save(@RequestBody VDFEvent vdfEvent) {
        vdfEventService.save(vdfEvent);
    }

//    @DeleteMapping(value = "/{id}")
//    public void delete(@PathVariable Long id) {
//        vdfEventService.delete(id);
//    }

}
