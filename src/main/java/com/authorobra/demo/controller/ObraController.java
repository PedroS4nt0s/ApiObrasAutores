package com.authorobra.demo.controller;

import com.authorobra.demo.dtos.AutorDto;
import com.authorobra.demo.dtos.ObraDto;
import com.authorobra.demo.entity.Autor;
import com.authorobra.demo.entity.Obra;
import com.authorobra.demo.service.ObraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/obra")
public class ObraController {

    @Autowired
    ObraService obraService;

    /*@GetMapping("/listar")
    public List<Obra> listarObras() {
        return obraService.todasAsObras();
    }*/

    @GetMapping("/listar")
    public ResponseEntity<List<ObraDto>> listarObras() {
        List obras = obraService.todasAsObras();
        return ResponseEntity.ok(obras);
    }


    @PostMapping("/criar")
    public ResponseEntity<ObraDto> criarObra(@Valid @RequestBody ObraDto obra) {
        ObraDto novaObra = obraService.criar(obra);
        return ResponseEntity.status(201).body(novaObra);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ObraDto> atualizarObra(@PathVariable Long id, @RequestBody ObraDto mudancasObra) {
        ObraDto obraAtualizada = obraService.atualizarObra(id, mudancasObra);
        return ResponseEntity.status(201).body(obraAtualizada);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<ObraDto> buscarPorId(@PathVariable Long id) {
        ObraDto obra = obraService.buscaPorId(id);
        return ResponseEntity.status(201).body(obra);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarAutor(@PathVariable Long id) {
        obraService.deletarObra(id);
        ResponseEntity.status(201);
    }

}

