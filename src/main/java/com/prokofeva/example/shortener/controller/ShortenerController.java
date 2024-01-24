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
    //+Переименовать, посмотри на названия сущностей и сервисов они работают с одной сущностью а контроллер вообще называется абстрактно
    private final LinkService linkService;

    @GetMapping("/{id}")//+информативней эндпоинт
    public RedirectView redirectToOriginalLink(@PathVariable("id") Long id) {
        return new RedirectView(linkService.getLink(id).getReference());
    }

    @PostMapping(value = "/create") //+переименовать
    @ResponseBody
    public ResponseEntity<LinkDto> createShortLink(@RequestBody LinkDto linkDto) {
        return ResponseEntity.status(HttpStatus.OK).body(linkService.createShortLink(linkDto));
    }
}

