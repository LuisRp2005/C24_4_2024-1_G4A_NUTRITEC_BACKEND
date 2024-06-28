package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.AsignacionComida;
import com.example.oauth2.Service.AsignacionComidaService;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> listarAsignacionPorId(@PathVariable Integer id) {
        try {
            AsignacionComida asignacionComida = asignacionComidaService.listarAsignacionPorId(id);
            return ResponseEntity.ok(asignacionComida.toString());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener la asignación de comida: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crearAsignacion(@RequestBody AsignacionComida asignacionComida) {
        try {
            // Validar que el objeto AsignacionComida tenga la comida asignada
            if (asignacionComida.getComida() == null || asignacionComida.getComida().getIdComida() == null) {
                return ResponseEntity.badRequest().body("El campo 'comida' o su ID no pueden ser nulos");
            }

            AsignacionComida nuevaAsignacion = asignacionComidaService.crearAsignacion(asignacionComida);
            return ResponseEntity.ok(nuevaAsignacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la asignación de comida: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAsignacion(@PathVariable Integer id, @RequestBody AsignacionComida asignacionComida) {
        try {
            // Validar que el objeto AsignacionComida tenga la comida asignada
            if (asignacionComida.getComida() == null || asignacionComida.getComida().getIdComida() == null) {
                return ResponseEntity.badRequest().body("El campo 'comida' o su ID no pueden ser nulos");
            }

            AsignacionComida actualizada = asignacionComidaService.actualizarAsignacion(id, asignacionComida);
            return ResponseEntity.ok(actualizada);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la asignación de comida: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsignacion(@PathVariable Integer id) {
        try {
            asignacionComidaService.eliminarAsignacion(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
