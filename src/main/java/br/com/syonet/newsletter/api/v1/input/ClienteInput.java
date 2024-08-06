package br.com.syonet.newsletter.api.v1.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteInput {


    @NotEmpty
    private String name;

    @Email
    private String email;

    private LocalDate birthday;

}
