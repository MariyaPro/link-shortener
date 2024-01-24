package com.prokofeva.example.shortener.doman;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LinkDto {
    private String reference;
    private String shortLink;
}
