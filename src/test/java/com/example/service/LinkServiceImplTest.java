package com.example.service;

import com.example.domain.Link;
import com.example.repository.LinkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.TestHelper.TEST_LINK;
import static com.example.TestHelper.TEST_LINK_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LinkServiceImplTest {

    @Mock
    LinkRepository linkRepository;
    @InjectMocks
    LinkServiceImpl linkService;

    @Test
    public void createIllegalLink() {

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> linkService.create("htteeep://"));
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> linkService.create("htt://"));
    }
    @Test
    public void createExistingLink() {

        when(linkRepository.findByLink(any())).thenReturn(Optional.of(TEST_LINK));
        linkService.create("https://habr.com/ru/all/");

        assertThat(TEST_LINK.getCount()).isEqualTo(2);
        verify(linkRepository).save(any());

        TEST_LINK.setCount(1L);
    }
    @Test
    public void createLink() {

        when(linkRepository.findAll()).thenReturn(TEST_LINK_LIST);
        String shortLink = linkService.create("https://stroki.ru/");
        assertTrue(shortLink.contains("http://localhost:8080/short/?id="));
        verify(linkRepository).save(any());
    }
    @Test
    public  void  getAllShortLinks() {

        when(linkRepository.findAll()).thenReturn(TEST_LINK_LIST);
        assertThat(linkService.getAllShortLinks()).isEqualTo(TEST_LINK_LIST.stream().map(Link::getShortLink).collect(Collectors.toList()));
    }
    @Test
    public void deleteNotExistingLink() {

        when(linkRepository.findByShortLink(any())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> linkService.delete(any()));
    }
    @Test
    public void deleteLink() {

        when(linkRepository.findByShortLink(any())).thenReturn(Optional.of(TEST_LINK));
        linkService.delete(any());
        verify(linkRepository).delete(any());
    }
    @Test
    public void  getInfoNotExistingLink() {
        when(linkRepository.findByShortLink(any())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> linkService.delete(any()));
    }
    @Test
    public  void getOriginalLink() {

        when(linkRepository.findByShortLink(any())).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> linkService.delete(any()));
    }

}