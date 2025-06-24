package com.josmejia2401.requiredremainder.domain.service;

import com.josmejia2401.requiredremainder.domain.port.RemainderPort;
import org.springframework.stereotype.Service;

/**
 * Implementación del puerto {@link RemainderPort} para calcular el mayor entero k
 * tal que 0 ≤ k ≤ n y k mod x = y, conforme al problema Codeforces 1374A.
 * <p>
 * Esta clase representa la lógica de dominio central y es completamente independiente
 * de frameworks, permitiendo su fácil prueba y reutilización en diferentes adaptadores
 * (por ejemplo, controladores web, interfaces de línea de comandos, etc.).
 * </p>
 *
 * <b>Lógica implementada:</b>
 * <ul>
 *   <li>Se utiliza la fórmula k = n - (n - y) % x para calcular el mayor valor posible de k que cumpla las restricciones del problema.</li>
 *   <li>Este método es eficiente (O(1)) y cubre todos los casos permitidos por las restricciones del enunciado.</li>
 * </ul>
 *
 * @author TuNombre
 * @see RemainderPort
 * @see <a href="https://codeforces.com/problemset/problem/1374/A">Codeforces 1374A - Required Remainder</a>
 */
public class RemainderService implements RemainderPort {

    /**
     * Calcula el mayor entero k tal que 0 ≤ k ≤ n y k mod x = y.
     * <p>
     * Utiliza la fórmula matemática eficiente: {@code k = n - (n - y) % x}.
     * Se asume que las restricciones del problema se cumplen: x ≥ 2, 0 ≤ y < x, y ≤ n.
     * </p>
     *
     * @param x Divisor de la operación módulo (debe ser ≥ 2)
     * @param y Resto deseado (debe ser 0 ≤ y < x)
     * @param n Límite superior para el valor de k (debe ser y ≤ n)
     * @return El mayor valor de k que cumple las condiciones establecidas
     */
    @Override
    public long compute(long x, long y, long n) {
        return n - (n - y) % x;
    }
}

