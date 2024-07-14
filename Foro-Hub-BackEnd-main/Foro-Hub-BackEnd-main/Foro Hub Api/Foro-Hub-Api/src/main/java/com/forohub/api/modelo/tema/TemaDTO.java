package com.forohub.api.modelo.tema;

import java.time.LocalDate;

public record TemaDTO(
         Long id,
         String titulo,
         String mensaje,
         LocalDate fechaDeCreacion,
         String autor,
         String curso
) {
    public TemaDTO(Tema tema){
        this(
                tema.getId(),
                tema.getTitulo(),
                tema.getMensaje(),
                tema.getFechaDeCreacion(),
                tema.getAutor(),
                tema.getCurso()
        );
    }
}
