package com.marvel.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name attribute is mandatory")
    private String name;
    private String description;
    private String superPowers;

    public Character(String name, String description, String superPowers) {
        super();
        this.name = name;
        this.description = description;
        this.superPowers = superPowers;
    }

}
