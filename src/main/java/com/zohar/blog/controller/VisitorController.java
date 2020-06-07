package com.zohar.blog.controller;

import com.zohar.blog.model.Visitor;
import com.zohar.blog.service.VisitorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Project:      com.zohar.blog.controller
 * Class:        blog
 * Description:
 * Time:         6/7/2020 8:30 PM
 *
 * @author zohar
 **/
@RestController
@RequestMapping(path = "/visitor")
@CrossOrigin(origins = "*")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping
    public List<Visitor> index() {
        return visitorService.getVisitorList();
    }

    @GetMapping("/frequency")
    public Integer frequency(String fingerprint) {
        return visitorService.frequency(fingerprint);
    }
}
