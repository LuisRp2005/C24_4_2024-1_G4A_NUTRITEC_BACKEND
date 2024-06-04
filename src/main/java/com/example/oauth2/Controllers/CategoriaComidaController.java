package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.CategoriaComida;
import com.example.oauth2.Service.CategoriaComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias-comida")
@CrossOrigin("*")
public class CategoriaComidaController {

    @Autowired
    private CategoriaComidaService categoriaComidaService;

    @GetMapping
    public List<CategoriaComida> listarTodasLasCategorias() {
        return categoriaComidaService.listarTodasLasCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaComida> listarCategoriaPorId(@PathVariable Integer id) {
        CategoriaComida categoriaComida = categoriaComidaService.listarCategoriaPorId(id);
        return ResponseEntity.ok(categoriaComida);
    }

    @PostMapping
    public CategoriaComida crearCategoria(@RequestBody CategoriaComida categoriaComida) {
        return categoriaComidaService.crearCategoria(categoriaComida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaComida> actualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaComida categoriaComida) {
        CategoriaComida actualizada = categoriaComidaService.actualizarCategoria(id, categoriaComida);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        categoriaComidaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
