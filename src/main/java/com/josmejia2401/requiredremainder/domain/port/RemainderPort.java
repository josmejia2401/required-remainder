package com.josmejia2401.requiredremainder.domain.port;

/**
 * Puerto de dominio para la operación de cálculo del resto requerido.
 * <p>
 * Define el contrato que debe implementar cualquier clase de servicio
 * responsable de calcular el mayor entero k tal que 0 ≤ k ≤ n y k mod x = y,
 * según el problema Codeforces 1374A.
 * </p>
 *
 * Este puerto permite desacoplar la lógica de dominio de la infraestructura
 * y los frameworks, siguiendo los principios de la arquitectura hexagonal.
 *
 * @see <a href="https://codeforces.com/problemset/problem/1374/A">Codeforces 1374A - Required Remainder</a>
 */
public interface RemainderPort {

    /**
     * Calcula el mayor entero k tal que 0 ≤ k ≤ n y k mod x = y.
     *
     * @param x Divisor de la operación módulo (x ≥ 2).
     * @param y Resto deseado (0 ≤ y < x).
     * @param n Límite superior para el valor de k (y ≤ n).
     * @return El mayor valor de k que cumple las condiciones.
     */
    long compute(long x, long y, long n);
}
