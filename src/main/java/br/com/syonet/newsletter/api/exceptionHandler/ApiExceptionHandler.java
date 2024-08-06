package br.com.syonet.newsletter.api.exceptionHandler;


import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERROR_GENERIC
            = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
            + "o problema persistir, entre em contato com o administrador do sistema.";


    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(MSG_ERROR_GENERIC)
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(MSG_ERROR_GENERIC)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }


    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
                                                        ProblemType problemType, String detail) {

        return Problem.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

    private String joinPath(List<Reference> references) {
        return references.stream()
                .map(Reference::getFieldName)
                .collect(Collectors.joining("."));
    }


}
