package br.com.syonet.newsletter.api.v1.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NoticiaInput {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    private String link;
}
