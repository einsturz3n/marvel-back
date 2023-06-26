package com.marvel.api.controllers;

import com.marvel.api.model.Character;
import com.marvel.api.responses.Response;
import com.marvel.api.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<Response<List<Character>>> listAll() {
        List<Character> characters = characterService.listAll();

        if (characters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(new Response<List<Character>>(characters));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Character>> getById(@PathVariable Long id) {
        Character character = this.characterService.listById(id);

        if (character == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new Response<Character>(character));
    }

    @GetMapping("/all")
    public List<Object> getCharacters() {
        String url = "https://gateway.marvel.com:443/v1/public/characters?ts=1&apikey=10fe8c8b1d77bb1c05fe46386710dfa1&hash=0b95afabd9fa769cb0cd760379d92959";
        RestTemplate restTemplate1 = new RestTemplate();
        Object[] objects = new Object[]{restTemplate1.getForObject(url, Object.class)};
        return Arrays.asList(objects);
    }

}
