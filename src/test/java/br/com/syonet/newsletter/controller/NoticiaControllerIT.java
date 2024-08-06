package br.com.syonet.newsletter.controller;

import br.com.syonet.newsletter.domain.model.Noticia;
import br.com.syonet.newsletter.utils.DatabaseCleaner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.yml")
@ActiveProfiles("test")
public class NoticiaControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "noticias";

        databaseCleaner.clearTables();

    }


    // titulo é obrigatório
    @Test
    void shouldTittleIsRequired_return_404() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        var body = Noticia.builder()
                .titulo(null)
                .descricao("Uma descricao qualquer")
                .link("http://dwed.com")
                .build();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .post(objectMapper.writeValueAsString(body))
                .then()
                .statusCode(HttpStatus.OK.value())
        ;
    }

    @Test
    void shouldDescriptionIsRequired_return_404() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
        ;
    }

    // link é uma url valida
    // link url é opcional


}
