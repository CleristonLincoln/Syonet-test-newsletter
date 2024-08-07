package br.com.syonet.newsletter.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteModel {

    private Long id;

    private String nome;

    private String email;

    private LocalDate birthday;

}
