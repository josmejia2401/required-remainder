package com.josmejia2401.requiredremainder.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

/**
 * Representa una solicitud para calcular el mayor entero k tal que 0 ≤ k ≤ n y k mod x = y,
 * según el enunciado del problema "Required Remainder" de Codeforces 1374A.
 * <p>
 * Cada campo representa uno de los parámetros de entrada necesarios para el cálculo.
 *
 * @param x Divisor de la operación módulo. Debe ser mayor o igual a 2.
 * @param y Resto deseado. Debe estar en el rango 0 ≤ y &lt; x.
 * @param n Límite superior para el valor de k. Debe cumplir y ≤ n.
 * @see <a href="https://codeforces.com/problemset/problem/1374/A">Codeforces 1374A</a>
 */
@Schema(description = "Solicitud para calcular el resto requerido")
public record Remainder(
        @Min(value = 2, message = "El divisor (x) debe ser mayor o igual a 2")
        @Schema(description = "Divisor para la operación módulo", example = "7", minimum = "2")
        long x,

        @Min(value = 0, message = "El resto (y) debe ser mayor o igual a 0")
        @Schema(description = "Resto deseado (0 ≤ y < x)", example = "5", minimum = "0")
        long y,

        @Min(value = 0, message = "El límite superior (n) debe ser mayor o igual a 0")
        @Schema(description = "Límite superior para el valor de k", example = "12345", minimum = "0")
        long n
) { }