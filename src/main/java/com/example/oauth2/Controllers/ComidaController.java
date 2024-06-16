package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.Comida;
import com.example.oauth2.Service.ComidaService;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/comida")
@CrossOrigin("*")
public class ComidaController {

    @Autowired
    private ComidaService comidaService;

    @GetMapping
    public List<Comida> listarTodasLasComidas() {
        return comidaService.listarTodasLasComidas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comida> listarComidaPorId(@PathVariable Integer id) {
        Comida comida = comidaService.listarComidaPorId(id);
        return ResponseEntity.ok(comida);
    }

    @PostMapping
    public Comida crearComida(@RequestBody Comida comida) {
        return comidaService.crearComida(comida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comida> actualizarComida(@PathVariable Integer id, @RequestBody Comida comidaRequest) {
        Comida comidaActualizada = comidaService.actualizarComida(id, comidaRequest);
        return ResponseEntity.ok(comidaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarComida(@PathVariable Integer id) {
        comidaService.eliminarComida(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
