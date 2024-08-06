package br.com.syonet.newsletter.api.v1.input;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
public class NoticiaInput {

    @NotNull
    private String titulo;

    @NotNull
    private String descricao;

    private String link;
}
