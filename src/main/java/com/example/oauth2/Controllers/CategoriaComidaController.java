package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.CategoriaComida;
import com.example.oauth2.Repository.CategoriaComidaRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CategoriaComidaController {

    @Autowired
    private CategoriaComidaRepository categoriaComidaRepository;

    @GetMapping("/categorias-comida")
    public List<CategoriaComida> listarTodasLasCategoriasComida() {
        return categoriaComidaRepository.findAll();
    }

    @GetMapping("/categorias-comida/{id}")
    public ResponseEntity<CategoriaComida> obtenerCategoriaComidaPorId(@PathVariable Integer id) {
        CategoriaComida categoriaComida = categoriaComidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría de comida no existe con id: " + id));
        return ResponseEntity.ok(categoriaComida);
    }

    @PostMapping("/categorias-comida")
    public CategoriaComida crearCategoriaComida(@RequestBody CategoriaComida categoriaComida) {
        return categoriaComidaRepository.save(categoriaComida);
    }

    @PutMapping("/categorias-comida/{id}")
    public ResponseEntity<CategoriaComida> actualizarCategoriaComida(@PathVariable Integer id, @RequestBody CategoriaComida categoriaComidaDetalles) {
        CategoriaComida categoriaComida = categoriaComidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría de comida no existe con id: " + id));

        categoriaComida.setNombreCategoria(categoriaComidaDetalles.getNombreCategoria());

        CategoriaComida categoriaComidaActualizada = categoriaComidaRepository.save(categoriaComida);
        return ResponseEntity.ok(categoriaComidaActualizada);
    }

    @DeleteMapping("/categorias-comida/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCategoriaComida(@PathVariable Integer id) {
        CategoriaComida categoriaComida = categoriaComidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría de comida no existe con id: " + id));

        categoriaComidaRepository.delete(categoriaComida);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
