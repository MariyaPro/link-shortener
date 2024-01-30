package com.prokofeva.example.shortener.controller;

import com.prokofeva.example.shortener.doman.LinkDto;
import com.prokofeva.example.shortener.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/sh.l")
@RequiredArgsConstructor
public class ShortenerController {
    private final LinkService linkService;

    @GetMapping("/{id}")
    public RedirectView redirectToOriginalLink(@PathVariable("id") Long id) {
        return new RedirectView(linkService.getLink(id).getReference());
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<LinkDto> createShortLink(@RequestBody LinkDto linkDto) {
        return ResponseEntity.status(HttpStatus.OK).body(linkService.createShortLink(linkDto));
    }
}

