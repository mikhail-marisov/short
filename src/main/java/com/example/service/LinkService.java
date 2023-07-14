package com.example.service;

import com.example.domain.Link;

import java.util.List;

public interface LinkService {

    String create(String link);
    List<String> getAllShortLinks();
    void delete(String shortLink);
    Link getInfo(String shortLink);
    List<String> getFirst20();
    String geyOriginalLink(String params);

}
