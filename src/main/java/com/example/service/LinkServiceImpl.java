package com.example.service;

import com.example.domain.Link;
import com.example.repository.LinkRepository;
import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {

    private LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    @Transactional
    public String create(String link) {

        String shortLink;
        Optional<Link> linkFromDb = linkRepository.findByLink(link);
        //Если для данного URL уже сгенерирована короткая ссылка, возвращаем ее и инкрементируем счетчик переходов по ней
        if (linkFromDb.isPresent()) {
            shortLink = linkFromDb.get().getShortLink();
            linkFromDb.get().increaseCount();
            linkRepository.save(linkFromDb.get());
            //linkRepository.flush();
        } else {
            //Если для данного URL нет короткой ссылки то генерим ее
            List<String> shortLinks = linkRepository.findAll().stream().map(Link::getShortLink).collect(Collectors.toList());

            String urlParam = RandomStringUtils.randomAlphanumeric(7);
            //System.out.println(urlParam);
            StringBuilder linkBuilder = new StringBuilder("http://localhost:8080/short/?id=");
            shortLink = linkBuilder.append(urlParam).toString();

            // Если так получилось что сгенерированная ссылка уже связана с другим исходным URL то перегенерируем
            if (shortLinks.contains(shortLink)) {
                while (shortLinks.contains(shortLink)) {
                    urlParam = RandomStringUtils.randomAlphanumeric(7);
                    shortLink = linkBuilder.append(urlParam).toString();
                }
            }

            Link newLink = new Link();
            newLink.setShortLink(shortLink);
            newLink.setLink(link);
            newLink.setCount(1L);
            linkRepository.save(newLink);
        }

        return shortLink;
    }

    @Override
    public List<String> getAllShortLinks() {
        return linkRepository.findAll().stream().map(Link::getShortLink).collect(Collectors.toList());
    }

    @Override
    public void delete(String shortLink) {
        Optional<Link> link = linkRepository.findByShortLink(shortLink);
        if (link.isPresent()) {
            linkRepository.delete(link.get());
        }
    }

    @Override
    public Link getInfo(String shortLink) {
        return linkRepository.findByShortLink(shortLink).orElseThrow(() -> new RuntimeException("Ссылка не найдена"));
    }

    @Override
    public List<String> getFirst20() {
        Comparator<Link> comp = (link, t1) -> link.getCount().compareTo(t1.getCount());
        return linkRepository.findAll().stream().sorted(comp.reversed()).map(Link::getLink).limit(20).collect(Collectors.toList());
    }

    @Override
    public String geyOriginalLink(String params) {

        StringBuilder strBuilder = new StringBuilder("http://localhost:8080/short/?id=");
        String shortLink = strBuilder.append(params).toString();

        return linkRepository.findByShortLink(shortLink).orElseThrow(() -> new RuntimeException("Данная короткая ссылка недействительна")).getLink();
    }

}
