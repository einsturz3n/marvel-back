package com.marvel.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class User {
    @Id
    private String username;
    private String password;
    @NotEmpty
    private String name;

}
