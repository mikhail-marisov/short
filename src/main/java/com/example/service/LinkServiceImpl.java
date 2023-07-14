package com.example.service;

import com.example.domain.Link;
import com.example.repository.LinkRepository;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Service
public class LinkServiceImpl implements LinkService {

    private LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    //@Transactional
    @Override
    @Transactional
    public String create(String link) {

        String shortLink;
        Optional<Link> linkFromDb = linkRepository.findByLink(link);
        if (linkFromDb.isPresent()) {
            shortLink = linkFromDb.get().getShortLink();
            linkFromDb.get().increaseCount();
            linkRepository.save(linkFromDb.get());
            //linkRepository.flush();
        } else {
            String encodedUrl = Base64.getUrlEncoder().encodeToString(link.getBytes());
            StringBuilder linkBuilder = new StringBuilder("http://localhost:8080/short/?id=");
            shortLink = linkBuilder.append(encodedUrl.substring(0, 3)).toString();
            Link newLink = new Link();
            newLink.setShortLink(shortLink);
            newLink.setLink(link);
            newLink.setCount(1L);
            linkRepository.save(newLink);
        }

        return shortLink;
    }
}
