package com.marvel.api.services.impl;

import com.marvel.api.model.Character;
import com.marvel.api.repository.CharacterRepository;
import com.marvel.api.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<Character> listAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character listById(Long id) {
        return characterRepository.findOne(id);
    }

}
