package com.prokofeva.example.shortener.doman;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LinkDto {
    @NotBlank
    @URL
    private String reference;
    private String shortLink;
}
