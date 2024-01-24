package com.prokofeva.example.shortener.service;

import com.prokofeva.example.shortener.doman.Link;
import com.prokofeva.example.shortener.doman.LinkDto;
import org.springframework.stereotype.Component;

@Component
public class LinkMapper {
    public LinkDto convertToDto(Link link) {
        LinkDto linkDto = new LinkDto();
        linkDto.setReference(link.getReference());
        return linkDto;//+ а обратно не надо для save?
    }

    public Link convertToEntity(LinkDto linkDto) {
        Link link = new Link();
        link.setReference(linkDto.getReference());
        return link;
    }
}
