package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.AsignacionComida;
import com.example.oauth2.Service.AsignacionComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignacion-comidas")
@CrossOrigin("*")
public class AsignacionComidaController {

    @Autowired
    private AsignacionComidaService asignacionComidaService;

    @GetMapping
    public List<AsignacionComida> listarTodasLasAsignaciones() {
        return asignacionComidaService.listarTodasLasAsignaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignacionComida> listarAsignacionPorId(@PathVariable Integer id) {
        AsignacionComida asignacionComida = asignacionComidaService.listarAsignacionPorId(id);
        return ResponseEntity.ok(asignacionComida);
    }

    @PostMapping
    public AsignacionComida crearAsignacion(@RequestBody AsignacionComida asignacionComida) {
        return asignacionComidaService.crearAsignacion(asignacionComida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignacionComida> actualizarAsignacion(@PathVariable Integer id, @RequestBody AsignacionComida asignacionComida) {
        AsignacionComida actualizada = asignacionComidaService.actualizarAsignacion(id, asignacionComida);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsignacion(@PathVariable Integer id) {
        asignacionComidaService.eliminarAsignacion(id);
        return ResponseEntity.noContent().build();
    }
}
