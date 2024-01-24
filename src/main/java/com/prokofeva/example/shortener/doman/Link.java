package com.prokofeva.example.shortener.doman;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reference")
//+ правила хорошего тона писать название колонки, если кто-то изменить название переменой колонка тоже поменяет название
    //+с название в виде строки это будет сложнее сделать
    private String reference;

    //Hibernate нужен пустой конструктор для создания сущностей, так что он должен быть (почитай про это и про аннотацию Lombok
    // @NoArgsConstructor
}
