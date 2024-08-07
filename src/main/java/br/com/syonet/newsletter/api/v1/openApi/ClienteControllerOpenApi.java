package br.com.syonet.newsletter.api.v1.openApi;

import br.com.syonet.newsletter.api.v1.input.ClienteInput;
import br.com.syonet.newsletter.api.v1.model.ClienteModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Tag(name = "Clientes")
public interface ClienteControllerOpenApi {

    @Operation(summary = "Busca uma cidade por Id",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "ID da cidade inválido",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Cidade não encontrada",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    ResponseEntity<Page<ClienteModel>> getAll(Pageable pageable);

    @Operation(summary = "Salvar um cliente")
    ResponseEntity<ClienteModel> create(ClienteInput clienteInput);

}
