package br.com.syonet.newsletter.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticiaModel {

    private Long id;

    private String titulo;

    private String descricao;

    // validar com o webclient
    private String link;
}
