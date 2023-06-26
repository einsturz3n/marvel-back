package com.marvel.api;

import com.marvel.api.model.Character;
import com.marvel.api.repository.CharacterRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CharacterControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @MockBean
    private CharacterRepository characterRepository;


    @TestConfiguration
    static class Config {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().basicAuthorization("test", "marvel");
        }
    }

    @Before
    public void setup() {
        Character character = new Character(1L, "Bucky", "Capitain's old friend", "Strength, Steel arm");
        BDDMockito.when(characterRepository.findOne(character.getId())).thenReturn(character);
    }

    @Test
    public void listCharactersWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401() {
        System.out.println(port);
        restTemplate = restTemplate.withBasicAuth("1", "1");
        ResponseEntity<String> response = restTemplate.getForEntity("/v1/characters/", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void listCharactersWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
        List<Character> characters = asList(new Character(1L, "Bucky", "Capitain's old friend", "Strength, Steel arm"),
                new Character(2L, "Peter", "Spider-man", "Smart and fast"));
        BDDMockito.when(characterRepository.findAll()).thenReturn(characters);
        ResponseEntity<String> response = restTemplate.getForEntity("/v1/characters/", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getCharactersByIdWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
        ResponseEntity<Character> response = restTemplate.getForEntity("/v1/characters/{id}", Character.class, 1L);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getCharactersByIdWhenUsernameAndPasswordAreCorrectAndCharacterDoesNotExistShouldReturnStatusCode404() {
        ResponseEntity<Character> response = restTemplate.getForEntity("/v1/characters/{id}", Character.class, -1);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

}
