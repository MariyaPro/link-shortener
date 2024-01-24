package com.prokofeva.example.shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<String> handleLinkNotFoundException(LinkNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); //сообщение с большой буквы  наверно
    }
    //Пакет exception предполагает хранение самих исключений а не обработчиков (переименуй пакет тогда) и твой обработчик сработает только с исключением
    //NoSuchElementException а какой у тебя выпадает в сервисе не понятно, скорей всего EntityNotFoundException, следовательно не отработает
    //в аргументах лучше передать параметр что бы лучше идентифицировать где проблемма, например Id
}
