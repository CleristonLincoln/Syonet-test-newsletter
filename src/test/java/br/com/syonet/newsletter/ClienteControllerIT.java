import br.com.syonet.newsletter.utils.DatabaseCleaner;
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
public class ClienteControllerIT {


    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "clientes";

        databaseCleaner.clearTables();

    }


    @Test
    void shouldListClientPageable_return_200() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
        ;
    }

    @Test
    void shouldNameIsRequired_404() {

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\"email\":\"email@de.com\",\"dataNascimento\":\"2020-01-20\"}")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detail", equalTo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente."))
                .body("fields.userMessage[0]", equalTo("Campo nome é obrigatório"))
                .body("fields.name[0]", equalTo("nome"))
        ;
    }

    @Test
    void shouldEmailIsValid_404() {
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\"nome\":\"Usuario\",\"email\":\"email@.com\",\"dataNascimento\":\"2020-01-20\"}")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detail", equalTo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente."))
                .body("fields.userMessage[0]", equalTo("Campo email esta inválido"))
                .body("fields.name[0]", equalTo("email"))
        ;
    }

    @Test
    void shouldEmailIsRequired_404() {
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\"nome\":\"Usuario\",\"dataNascimento\":\"2020-01-20\"}")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detail", equalTo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente."))
                .body("fields.userMessage[0]", equalTo("Campo email é obrigatório"))
                .body("fields.name[0]", equalTo("email"))
        ;
    }


    @Test
    void shouldFormatDateBirthdayIsNotValid_404() {
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\"nome\":\"Usuario\",\"email\":\"email@.com\",\"dataNascimento\":\"2020-01-40\"}")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detail", equalTo("A data fornecida está em um formato inválido. Verifique o valor da data."))
                .body("userMessage", equalTo("Por favor, forneça uma data válida no formato correto."))
        ;
    }


    @Test
    void shouldDateBirthdayIsOptional_201() {
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\"nome\":\"Usuario\",\"email\":\"email@edasd.com\"}")
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("nome", equalTo("Usuario"))
        ;
    }


}
