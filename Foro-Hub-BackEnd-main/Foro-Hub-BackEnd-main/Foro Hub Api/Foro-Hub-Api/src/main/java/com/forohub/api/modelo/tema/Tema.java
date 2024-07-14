package com.forohub.api.modelo.tema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tema {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String titulo;
   private String mensaje;
   @Column(name = "fecha_de_creacion")
   private LocalDate fechaDeCreacion;
   private Boolean status; /*(estado del tópico)*/
   private String autor;
   private String curso;

   public Tema(DatosRegistroTema datos) {
      this.titulo = datos.titulo();
      this.mensaje = datos.mensaje();
      this.fechaDeCreacion = datos.fechaDeCreacion();
      this.status = datos.status();
      this.autor = datos.autor();
      this.curso = datos.curso();

   }

   public void actualizarDatos(ActualizarTemaDTO datos) {
      if(datos.titulo() != null) this.titulo = datos.titulo();
      if(datos.mensaje() != null) this.mensaje = datos.mensaje();
      if(datos.fechaDeCreacion() != null) this.fechaDeCreacion = datos.fechaDeCreacion();
      if(datos.autor() != null) this.autor = datos.autor();
      if(datos.curso() != null) this.curso = datos.curso();
   }

    public void desactivarTopico() {
      this.status = false;
    }
}


