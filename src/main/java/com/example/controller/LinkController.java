package com.example.controller;

import com.example.domain.Link;
import com.example.service.LinkService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<Objects> getOriginalLink(@RequestParam String id) throws URISyntaxException {

        URI extUri = new URI(linkService.geyOriginalLink(id));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(extUri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

    }

    @PostMapping("/getInfo")
    public Link getShortLinkInfo(@RequestBody String shortLink) {
        return linkService.getInfo(shortLink);
    }

    @GetMapping("/first20")
    public List<String> getFirst20() {
        return linkService.getFirst20();
    }
}
