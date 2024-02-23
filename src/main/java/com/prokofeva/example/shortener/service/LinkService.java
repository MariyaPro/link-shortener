package com.prokofeva.example.shortener.service;

import com.prokofeva.example.shortener.doman.Link;
import com.prokofeva.example.shortener.doman.LinkDto;
import com.prokofeva.example.shortener.exception.LinkNotFoundException;
import com.prokofeva.example.shortener.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final LinkRepository linkRepository;
    private final LinkMapper linkMapper;

    @Value("${servicePrefix}")
    @Setter
    private String linkPrefix;

    public LinkDto getLink(Long id) {
        return linkMapper.convertToDto(linkRepository.findById(id).orElseThrow(() -> new LinkNotFoundException(id)));
    }

    public LinkDto createShortLink(LinkDto linkDto) {
        Link link = linkRepository.save(linkMapper.convertToEntity(linkDto));
        linkDto.setShortLink(linkPrefix + link.getId());
        return linkDto;
    }
}
