
package com.ejercicios.sesion7_8_9.controller;

import com.ejercicios.sesion10_11_12.entities.Laptop;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LaptopControllerTest {
    
    private TestRestTemplate testRestTemplate;
    
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    
    @LocalServerPort
    private int port;
    
    @BeforeEach
    void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }
    
    @DisplayName("Comprobar finAll()")
    @Test
    void finAll(){
        ResponseEntity<Laptop[]> response = 
                testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        
        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());
    }
    
    @DisplayName("Comprobar findOneById()")
    @Test
    void findOneById(){
        ResponseEntity<Laptop> response = 
                testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @DisplayName("Comprobar create()")
    @Test
    void create(){
        //creando la cabecera
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        // creando el body
        String json = """
                      {
                            "marca": "Laptop create",
                            "procesador": "i7",
                            "ram": 16,
                            "capacidadDiscoDuro": 500,
                            "sistemaOperativo": false,
                            "price": 678.99,
                            "releaseDate": "2023-06-05"
                           }
                      """;
        
        // uno cabecera y body
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        ResponseEntity<Laptop> response = 
                testRestTemplate.exchange("/api/laptops", 
                    HttpMethod.POST,
                    request,
                    Laptop.class);
        
        Laptop result = response.getBody();
        
        assertEquals(1L, result.getId());
        assertEquals("Laptop create", result.getMarca());
    }

}
