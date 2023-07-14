package com.example.controller;

import com.example.domain.Link;
import com.example.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/short/")
public class LinkController {

    LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String  create(@RequestBody String link) {
        String shortLink = linkService.create(link);
        return shortLink;
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllShortLinks() {
        return linkService.getAllShortLinks();
    }

    @PostMapping("/delete")
    public void deleteLink(@RequestBody String shortLink) {
        linkService.delete(shortLink);
    }

    @GetMapping()
    public String getOriginalLink(@RequestParam String id) {
        return id;
    }
}
