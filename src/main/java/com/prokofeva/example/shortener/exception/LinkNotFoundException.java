package com.prokofeva.example.shortener.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LinkNotFoundException extends RuntimeException{
    private  String message;

    public LinkNotFoundException (Long id){
        message ="http://sh.l/" + id + " link not found";
    }
}
