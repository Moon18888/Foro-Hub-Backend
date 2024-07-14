package com.forohub.api.modelo.tema;

import java.time.LocalDate;

public record DatosRespuestaTema(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaDeCreacion,
        Boolean status,
        String autor,
        String curso
) {}
