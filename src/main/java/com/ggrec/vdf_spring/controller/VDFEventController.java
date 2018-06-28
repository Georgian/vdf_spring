package com.ggrec.vdf_spring.controller;

import com.ggrec.vdf_spring.domain.VDFAthlete;
import com.ggrec.vdf_spring.repository.VDFAthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/event")
public class VDFEventController {


    private VDFAthleteRepository vdfAthleteRepository;

    @Autowired
    public VDFEventController(VDFAthleteRepository vdfAthleteRepository) {
        this.vdfAthleteRepository = vdfAthleteRepository;
    }

    @GetMapping(path="/test")
    public List<VDFAthlete> test(HttpServletRequest httpServletRequest) {
        return vdfAthleteRepository.findAll();
    }

}
