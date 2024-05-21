package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.Comida;
import com.example.oauth2.Repository.ComidaRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ComidaController {

    @Autowired
    private ComidaRepository comidaRepository;

    @GetMapping("/comida")
    public List<Comida> listarTodasLasComidas() {
        return comidaRepository.findAll();
    }

    @GetMapping("/comida/{id}")
    public ResponseEntity<Comida> listarComidaPorId(@PathVariable Integer id) {
        Comida comida = comidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La comida no existe con id : " + id));
        return ResponseEntity.ok(comida);
    }

    @PostMapping("/comida")
    public Comida crearComida(@RequestBody Comida comida) {
        return comidaRepository.save(comida);
    }

    @PutMapping("/comida/{id}")
    public ResponseEntity<Comida> actualizarComida(@PathVariable Integer id, @RequestBody Comida comidaRequest) {
        Comida comida = comidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La comida no existe con id : " + id));

        comida.setNombreComida(comidaRequest.getNombreComida());
        comida.setCategoriaComida(comidaRequest.getCategoriaComida());
        comida.setCalorias(comidaRequest.getCalorias());

        Comida comidaActualizada = comidaRepository.save(comida);
        return ResponseEntity.ok(comidaActualizada);
    }

    @DeleteMapping("/comida/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarComida(@PathVariable Integer id) {
        Comida comida = comidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La comida no existe con id : " + id));

        comidaRepository.delete(comida);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
