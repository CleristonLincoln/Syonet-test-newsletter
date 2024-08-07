package br.com.syonet.newsletter.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTITY_NOT_READ("entidade-nao-legivel", "Entidade não legível"),
    INVALID_DATA("dados-invalidos", "Dados Inválidos"),
    INVALID_PARAM("parametro-invalido", "Parametro Inválido"),
    OPERATION_NOT_PERMIT("operacao-nao-permitida", "Operação não permitida"),
    ERROR_DE_SYSTEM("sistema-erro", "Erro no sistema");


    private final String title;
    private final String uri;

    ProblemType(String uri, String title) {
        this.uri = "https://syonet.com.br/" + uri;
        this.title = title;
    }
}
