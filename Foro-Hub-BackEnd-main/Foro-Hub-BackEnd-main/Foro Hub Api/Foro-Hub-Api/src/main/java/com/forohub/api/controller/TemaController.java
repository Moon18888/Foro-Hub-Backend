package com.forohub.api.controller;

import com.forohub.api.modelo.tema.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;

import java.net.URI;

@RestController
@RequestMapping("/topico")
public class TemaController {

    @Autowired
    private TemaRepository repository;

    @GetMapping
    @Transactional
    @Operation(
            summary = "Consultar todos los registros de topico",
            tags = {"Topico Controller", "GET"}
    )
    public ResponseEntity<Page<TemaDTO>> listadoTopico(@PageableDefault(size = 10) Pageable paginacion){

        Page<Tema> topicos = repository.findByStatusTrue(paginacion);
        if(topicos.isEmpty()){
            return ResponseEntity.ok(Page.empty(paginacion));
        }else {
            Page<TemaDTO> topicoDTOS = topicos.map(TemaDTO::new);
            return ResponseEntity.ok(topicoDTOS);
        }

    }

    @GetMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Consultar registro por identificador unico ID",
            tags = {"Topico Controller", "GET"}
    )
    public ResponseEntity<DatosRespuestaTema> retornarRegistroUnico(@PathVariable Long id){
        Tema tema = repository.getReferenceById(id);
        var datosTopicos = new DatosRespuestaTema(
                tema.getId(),
                tema.getTitulo(),
                tema.getMensaje(),
                tema.getFechaDeCreacion(),
                tema.getStatus(),
                tema.getAutor(),
                tema.getCurso());
        return ResponseEntity.ok(datosTopicos);
    }

    @PostMapping
    @Transactional
    @Operation(
            summary = "Registrar un topico en base de datos",
            tags = {"Topico Controller", "POST"}
    )
    public ResponseEntity<DatosRespuestaTema> registroTopicos(@RequestBody DatosRegistroTema datosRegistroTema, UriComponentsBuilder uriComponentsBuilder){
        Tema tema = repository.save(new Tema(datosRegistroTema));
        DatosRespuestaTema datos = new DatosRespuestaTema(
                tema.getId(),
                tema.getTitulo(),
                tema.getMensaje(),
                tema.getFechaDeCreacion(),
                tema.getStatus(),
                tema.getAutor(),
                tema.getCurso());
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(tema.getId()).toUri();
        return ResponseEntity.created(url).body(datos);
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "Actualizar un topico",
            tags = {"Topico Controller", "PUT"}
    )
    public ResponseEntity<DatosRespuestaTema> actualizarTopico(
            @RequestBody ActualizarTemaDTO actualizarTemaDTO){

        Tema tema = repository.findById(actualizarTemaDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar com.forohub.api.modelo.topico.Topico con id: -> " + actualizarTemaDTO.id()));

        if(actualizarTemaDTO.id() == null) {
            throw new IllegalArgumentException("ID topico es requerido");
        }

        tema.actualizarDatos(actualizarTemaDTO);
        repository.save(tema);

        return ResponseEntity.ok()
                .body(new DatosRespuestaTema(
                    tema.getId(),
                    tema.getTitulo(),
                    tema.getMensaje(),
                    tema.getFechaDeCreacion(),
                    tema.getStatus(),
                    tema.getAutor(),
                    tema.getCurso()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Eliminar de forma logica un registro topico",
            tags = {"Topico Controller", "DELETE"}
    )
    public ResponseEntity<Tema> eliminarTopico(@PathVariable Long id){
        Tema tema = repository.getReferenceById(id);
        tema.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

}


/*//Page<TopicoDTO> topicoDTOS = repository.findByStatusTrue(paginacion).map(TopicoDTO::new);
        Page<Topico> topicos = repository.findByStatusTrue(paginacion);
        if(topicos.isEmpty()){
            return ResponseEntity.ok(Page.empty(paginacion));
        }else {
            Page<TopicoDTO> topicoDTOS = topicos.map(TopicoDTO::new);
            return ResponseEntity.ok(topicoDTOS);
        }
        //return ResponseEntity.ok(repository.findByStatusTrue(paginacion).map(TopicoDTO::new));*/