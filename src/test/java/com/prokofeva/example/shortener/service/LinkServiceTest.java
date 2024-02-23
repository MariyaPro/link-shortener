package com.prokofeva.example.shortener.service;

import com.prokofeva.example.shortener.doman.Link;
import com.prokofeva.example.shortener.doman.LinkDto;
import com.prokofeva.example.shortener.exception.LinkNotFoundException;
import com.prokofeva.example.shortener.repository.LinkRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LinkServiceTest {

    @Mock
    private LinkMapper linkMapper;

    @Mock
    private LinkRepository linkRepository;

    @InjectMocks
    private LinkService linkService;

    private static LinkDto linkDto;
    private static Link linkEntity;

    @BeforeAll
    public static void prepareData() {
        linkDto = new LinkDto();
        linkDto.setReference("https://javarush.com/groups/posts/2500-vse-o-unit-testing-metodiki-ponjatija-praktika");

        linkEntity = new Link();
        linkEntity.setReference("https://javarush.com/groups/posts/2500-vse-o-unit-testing-metodiki-ponjatija-praktika");
        linkEntity.setId(10L);
    }

    @Test
    public void createShortLinkTest() {
        String expectShortLink = "http://test/10";
        linkService.setLinkPrefix("http://test/");

        when(linkRepository.save(any())).thenReturn(linkEntity);
        when(linkMapper.convertToDto(linkEntity)).thenReturn(linkDto);
        LinkDto linkDtoActual = linkService.createShortLink(linkDto);

        verify(linkRepository, times(1)).save(any());
        verify(linkMapper, times(1)).convertToEntity(any());

        assertNotNull(linkDtoActual);
        assertArrayEquals(expectShortLink.getBytes(StandardCharsets.UTF_8), linkDtoActual.getShortLink().getBytes());
    }

    @Test
    public void getLinkTest() {
        when(linkRepository.findById(10L)).thenReturn(Optional.ofNullable(linkEntity));
        when(linkMapper.convertToDto(linkEntity)).thenReturn(linkDto);
        LinkDto linkDtoActual = linkService.getLink(10L);

        verify(linkRepository, times(1)).findById(10L);
        verify(linkMapper, times(1)).convertToDto(linkEntity);

        assertNotNull(linkDtoActual);
        assertEquals(linkDto, linkDtoActual);
    }

    @Test
    public void getLinkFailTest() {
        when(linkRepository.findById(100L)).thenReturn(Optional.empty());
        LinkDto linkDtoActual = null;
        try {
            linkDtoActual = linkService.getLink(100L);
        } catch (Exception e) {
            assertEquals(LinkNotFoundException.class, e.getClass());
            verify(linkRepository, times(1)).findById(100L);
        }
        assertNull(linkDtoActual);
    }

}