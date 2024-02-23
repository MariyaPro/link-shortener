package com.prokofeva.example.shortener.service;

import com.prokofeva.example.shortener.doman.Link;
import com.prokofeva.example.shortener.doman.LinkDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
@SpringBootTest
public class LinkMapperTest {
    @Autowired
    private LinkMapper linkMapper;

    @Test
    public void convertToDtoTest() {
        Link linkIn = new Link();
        linkIn.setReference("https://original.link");
        linkIn.setId(10L);

        LinkDto expectedLinkDto = new LinkDto();
        expectedLinkDto.setReference("https://original.link");

        LinkDto linkDtoFromTest = linkMapper.convertToDto(linkIn);

        assertNotNull(linkDtoFromTest);
        assertArrayEquals(expectedLinkDto.getReference().getBytes(StandardCharsets.UTF_8)
                , linkDtoFromTest.getReference().getBytes());
        assertNull(linkDtoFromTest.getShortLink());
        assertEquals(expectedLinkDto, linkDtoFromTest);
    }

    @Test
    public void convertToEntityTest() {
        LinkDto linkDtoIn = new LinkDto();
        linkDtoIn.setReference("https://original.link");

        Link expectedLink = new Link();
        expectedLink.setReference("https://original.link");

        Link linkEntityFromTest = linkMapper.convertToEntity(linkDtoIn);

        assertNotNull(linkEntityFromTest);
        assertArrayEquals(expectedLink.getReference().getBytes(StandardCharsets.UTF_8)
                , linkEntityFromTest.getReference().getBytes());
        assertEquals(0L, linkEntityFromTest.getId());
        assertEquals(expectedLink, linkEntityFromTest);
    }
}
