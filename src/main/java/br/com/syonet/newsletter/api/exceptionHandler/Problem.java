package br.com.syonet.newsletter.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private OffsetDateTime timestamp;
    private String detail;
    private String userMessage;
    private List<FieldCustom> fields;

    @Getter
    @Builder
    public static class FieldCustom {

        private String name;

        private String userMessage;

    }

}
