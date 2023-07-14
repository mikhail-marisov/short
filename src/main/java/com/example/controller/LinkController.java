package com.example.controller;

import com.example.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/short/")
public class LinkController {

    LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public String  create(@RequestBody String link) {
        String shortLink = linkService.create(link);
        return shortLink;
    }

    @GetMapping()
    public String getOriginalLink(@RequestParam String id) {
        return id;
    }
}
