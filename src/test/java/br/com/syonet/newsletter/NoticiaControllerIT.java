import br.com.syonet.newsletter.api.v1.input.NoticiaInput;
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
import static org.hamcrest.Matchers.equalTo;

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

        var body = NoticiaInput.builder()
                .titulo(null)
                .descricao("Uma descricao qualquer")
                .link("http://www.dwed.com")
                .build();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(body))
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("fields.name[0]", equalTo("titulo"))
                .body("detail", equalTo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente."))
                .body("fields.userMessage[0]", equalTo("Campo titulo é obrigatório"))
        ;
    }

    @Test
    void shouldDescriptionIsRequired_return_404() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        var body = NoticiaInput.builder()
                .titulo(" titulo aleatorio")
                .link("http://www.dwed.com")
                .build();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(body))
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("fields.name[0]", equalTo("descricao"))
                .body("detail", equalTo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente."))
                .body("fields.userMessage[0]", equalTo("Campo descrição é obrigatório"))
        ;
    }

    @Test
    void shouldLinkInvalid_return_404() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        var body = NoticiaInput.builder()
                .titulo(" titulo aleatorio")
                .descricao("uma descicao qualquer")
                .link("http://www.dweedddddddm")
                .build();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(body))
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detail", equalTo("Link informado é inválido."))
                .body("userMessage", equalTo("Link informado é inválido."))
        ;
    }
    // link url é opcional

    @Test
    void shouldCreatedNoticia_return_201() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        var body = NoticiaInput.builder()
                .titulo(" titulo aleatorio")
                .descricao("uma descicao qualquer")
                .build();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(body))
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("descricao", equalTo("uma descicao qualquer"))
        ;
    }
}
