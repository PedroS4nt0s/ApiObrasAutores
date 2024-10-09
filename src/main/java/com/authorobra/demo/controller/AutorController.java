package com.authorobra.demo.controller;

import com.authorobra.demo.dtos.AutorDto;
import com.authorobra.demo.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/autor")
public class AutorController {

    @Autowired
    AutorService autorService;

    @GetMapping("/listar")
    public ResponseEntity<List<AutorDto>> listarAutores() {
        List autores = autorService.todosAutores();
        return ResponseEntity.ok(autores);
    }

    @PostMapping("/criar")
    public ResponseEntity<AutorDto> criarAutor(@Valid @RequestBody AutorDto autor) {
        AutorDto novoAutor = autorService.criar(autor);
        return ResponseEntity.status(201).body(novoAutor);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AutorDto> atualizarAutor(@PathVariable Long id, @RequestBody AutorDto mudancasAutor) {
        AutorDto autorAtualizado = autorService.atualizarAutor(id, mudancasAutor);
        return ResponseEntity.status(201).body(autorAtualizado);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<AutorDto> buscarPorId(@PathVariable Long id) {
        AutorDto autor = autorService.buscaPorId(id);
        return ResponseEntity.status(201).body(autor);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarAutor(@PathVariable Long id) {
        autorService.deletarAutor(id);
        ResponseEntity.status(201);
    }

}
