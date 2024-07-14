package com.forohub.api.modelo.tema;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ActualizarTemaDTO(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaDeCreacion,
        String autor,
        String curso
) {}
