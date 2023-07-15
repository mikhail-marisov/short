package com.example.controller;

import com.example.domain.Link;
import com.example.service.LinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "LinkController", description = "Единственный контроллер в приложении")
@RestController
@RequestMapping("/short/")
public class LinkController {

    LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @Operation(summary = "Создать короткую ссылку", description = "Позволяет создавать короткие ссылки")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String  create(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Исходная ссылка") String link) {
        String shortLink = linkService.create(link);
        return shortLink;
    }

    @Operation(summary = "Получить все короткие ссылки", description = "Получить все короткие ссылки")
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllShortLinks() {
        return linkService.getAllShortLinks();
    }

    @Operation(summary = "Удалить ссылку", description = "Удаляет короткую и оригинальную ссылки из хранилиЩА")
    @PostMapping("/delete")
    public void deleteLink(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Короткая ссылка для удаления") String shortLink) {
        linkService.delete(shortLink);
    }

    @Operation(summary = "Редирект на исходную ссылку", description = "Переход по короткой ссылке и осуществление редиректа на связанную исходную ссылку")
    @GetMapping()
    public ResponseEntity<Objects> getOriginalLink(@RequestParam @Parameter(description = "очень короткая ссылка") String id) {

        URI extUri;
        try {
            extUri = new URI(linkService.getOriginalLink(id));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(extUri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

    }

    @Operation(summary = "Получение детальной информации по ссылке", description = "")
    @PostMapping("/getInfo")
    public Link getShortLinkInfo(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "очень короткая ссылка") String shortLink) {
        return linkService.getInfo(shortLink);
    }

    @Operation(summary = "Получение топ 20 сайтов по количеству переходов на них", description = "20")
    @GetMapping("/first20")
    public List<String> getFirst20() {
        return linkService.getFirst20();
    }
}
