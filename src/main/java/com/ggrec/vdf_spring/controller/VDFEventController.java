package com.ggrec.vdf_spring.controller;

import com.ggrec.vdf_spring.domain.VDFEvent;
import com.ggrec.vdf_spring.domain.VDFEventTag;
import com.ggrec.vdf_spring.service.VDFEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<VDFEvent> getAll(
            @RequestParam(value = "sport", required = false) List<String> sports,
            @RequestParam(value = "discipline", required =  false) List<String> disciplines,
            @RequestParam(value = "organizer", required = false) List<String> organizers,
            @RequestParam(value = "query", required = false) String query
    ) {

        return vdfEventService.getAll(query, sports, disciplines, organizers);
    }

    @GetMapping("/{id}")
    public VDFEvent getById(@PathVariable("id") Long id) {
        return vdfEventService.getById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") long id, @RequestBody VDFEvent event) {
        return vdfEventService.update(id, event)
                .map(e -> ResponseEntity.ok().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tags")
    public List<VDFEventTag> tags() {
        return vdfEventService.getAllTags();
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
