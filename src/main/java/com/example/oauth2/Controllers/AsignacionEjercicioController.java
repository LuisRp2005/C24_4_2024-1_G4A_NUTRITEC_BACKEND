package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.AsignacionEjercicio;
import com.example.oauth2.Service.AsignacionEjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignacion-ejercicios")
@CrossOrigin("*")
public class AsignacionEjercicioController {

    @Autowired
    private AsignacionEjercicioService asignacionEjercicioService;

    @GetMapping
    public List<AsignacionEjercicio> listarTodasLasAsignaciones() {
        return asignacionEjercicioService.listarTodasLasAsignaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignacionEjercicio> listarAsignacionPorId(@PathVariable Integer id) {
        AsignacionEjercicio asignacionEjercicio = asignacionEjercicioService.listarAsignacionPorId(id);
        return ResponseEntity.ok(asignacionEjercicio);
    }

    @PostMapping
    public ResponseEntity<?> crearAsignacion(@RequestBody AsignacionEjercicio asignacionEjercicio) {
        try {
            AsignacionEjercicio nuevaAsignacion = asignacionEjercicioService.crearAsignacion(asignacionEjercicio);
            return ResponseEntity.ok(nuevaAsignacion);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Devolver un mensaje de error genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la asignación de ejercicio.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignacionEjercicio> actualizarAsignacion(@PathVariable Integer id, @RequestBody AsignacionEjercicio asignacionEjercicio) {
        AsignacionEjercicio actualizada = asignacionEjercicioService.actualizarAsignacion(id, asignacionEjercicio);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsignacion(@PathVariable Integer id) {
        asignacionEjercicioService.eliminarAsignacion(id);
        return ResponseEntity.noContent().build();
    }
}
