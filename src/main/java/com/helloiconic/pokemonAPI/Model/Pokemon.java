package com.helloiconic.pokemonAPI.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Pokemon {
    @Id
    @GeneratedValue
    public Long id;

    @NotEmpty
    public String name;
    @NotEmpty
    public String type;
    @Min(value=1, message = "Pokemon Number should be greater than zero")
    public Long number;

    public Pokemon(){}
    public Pokemon (Long number, String name, String type) {
        this.number = number;
        this.type = type;
        this.name = name;
    }
}
