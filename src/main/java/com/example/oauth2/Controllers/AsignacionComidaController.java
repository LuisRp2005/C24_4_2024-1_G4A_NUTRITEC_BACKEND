package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.AsignacionComida;
import com.example.oauth2.Repository.AsignacionComidaRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AsignacionComidaController {

    @Autowired
    private AsignacionComidaRepository asignacionComidaRepository;

    @GetMapping("/asignaciones-comida")
    public List<AsignacionComida> listarTodasLasAsignacionesComida() {
        return asignacionComidaRepository.findAll();
    }

    @GetMapping("/asignaciones-comida/{id}")
    public ResponseEntity<AsignacionComida> obtenerAsignacionComidaPorId(@PathVariable Integer id) {
        AsignacionComida asignacionComida = asignacionComidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La asignación de comida no existe con id: " + id));
        return ResponseEntity.ok(asignacionComida);
    }

    @PostMapping("/asignaciones-comida")
    public AsignacionComida crearAsignacionComida(@RequestBody AsignacionComida asignacionComida) {
        return asignacionComidaRepository.save(asignacionComida);
    }

    @PutMapping("/asignaciones-comida/{id}")
    public ResponseEntity<AsignacionComida> actualizarAsignacionComida(@PathVariable Integer id, @RequestBody AsignacionComida asignacionComidaDetalles) {
        AsignacionComida asignacionComida = asignacionComidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La asignación de comida no existe con id: " + id));

        asignacionComida.setUsuario(asignacionComidaDetalles.getUsuario());
        asignacionComida.setComida(asignacionComidaDetalles.getComida());
        asignacionComida.setFechaHoraRegistro(asignacionComidaDetalles.getFechaHoraRegistro());

        AsignacionComida asignacionComidaActualizada = asignacionComidaRepository.save(asignacionComida);
        return ResponseEntity.ok(asignacionComidaActualizada);
    }

    @DeleteMapping("/asignaciones-comida/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarAsignacionComida(@PathVariable Integer id) {
        AsignacionComida asignacionComida = asignacionComidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La asignación de comida no existe con id: " + id));

        asignacionComidaRepository.delete(asignacionComida);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
