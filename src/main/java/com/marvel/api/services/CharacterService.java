package com.marvel.api.services;

import com.marvel.api.model.Character;

import java.util.List;

public interface CharacterService {

    List<Character> listAll();

    Character listById(Long id);

}
