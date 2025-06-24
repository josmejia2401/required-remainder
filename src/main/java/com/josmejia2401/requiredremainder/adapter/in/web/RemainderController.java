package com.josmejia2401.requiredremainder.adapter.in.web;

import com.josmejia2401.requiredremainder.domain.model.Remainder;
import com.josmejia2401.requiredremainder.domain.port.RemainderPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para la operación de Resto Requerido (Required Remainder).
 *
 * Expone un endpoint para procesar múltiples cálculos de restos requeridos en lote.
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Resto Requerido", description = "Operaciones para calcular el resto requerido según el problema Codeforces 1374A")
public class RemainderController {

    private final RemainderPort remainderPort;

    /**
     * Constructor para inyección de dependencias.
     *
     * @param remainderPort puerto de dominio para calcular el resto requerido
     */
    public RemainderController(RemainderPort remainderPort) {
        this.remainderPort = remainderPort;
    }

    /**
     * Procesa un lote de solicitudes de cálculo de resto requerido.
     *
     * @param requests Lista de solicitudes, cada una especificando el divisor (x), el resto deseado (y) y el límite superior (n).
     * @return Lista de resultados, donde cada valor es el mayor entero k tal que 0 ≤ k ≤ n y k mod x = y.
     *
     * @see <a href="https://codeforces.com/problemset/problem/1374/A">Problema Codeforces 1374A</a>
     */
    @Operation(
            summary = "Cálculo en lote del resto requerido",
            description = "Recibe una lista de solicitudes y retorna, para cada una, el mayor entero k tal que 0 ≤ k ≤ n y k mod x = y.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Lista de solicitudes de cálculo de resto requerido",
                    required = true,
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Remainder.class))
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de resultados para cada entrada",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = Long.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Entrada inválida"
                    )
            }
    )
    @PostMapping("/required-remainder/batch")
    public ResponseEntity<List<Long>> batch(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Lista de solicitudes de resto requerido.",
                    required = true
            )
            @RequestBody @Valid List<Remainder> requests) {
        List<Long> results = requests.stream()
                .map(req -> remainderPort.compute(req.x(), req.y(), req.n()))
                .toList();
        return ResponseEntity.ok(results);
    }
}

