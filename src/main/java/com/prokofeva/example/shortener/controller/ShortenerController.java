package com.prokofeva.example.shortener.controller;

import com.prokofeva.example.shortener.doman.LinkDto;
import com.prokofeva.example.shortener.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/sh.l")
@RequiredArgsConstructor
public class ShortenerController {
    private final LinkService linkService;

    @GetMapping("/{id}")
    public RedirectView redirectToOriginalLink(@PathVariable("id") @Positive Long id) {
        return new RedirectView(linkService.getLink(id).getReference());
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<LinkDto> createShortLink(@Valid @RequestBody LinkDto linkDto) {
        return ResponseEntity.status(HttpStatus.OK).body(linkService.createShortLink(linkDto));
    }
}

