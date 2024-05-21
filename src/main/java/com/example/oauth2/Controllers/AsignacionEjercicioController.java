package com.example.oauth2.Controllers;


import com.example.oauth2.Modelo.AsignacionEjercicio;
import com.example.oauth2.Repository.AsignacionEjercicioRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AsignacionEjercicioController {

    @Autowired
    private AsignacionEjercicioRepository asignacionEjercicioRepository;

    @GetMapping("/asignaciones-ejercicio")
    public List<AsignacionEjercicio> listarTodasLasAsignacionesEjercicio() {
        return asignacionEjercicioRepository.findAll();
    }

    @GetMapping("/asignaciones-ejercicio/{id}")
    public ResponseEntity<AsignacionEjercicio> obtenerAsignacionEjercicioPorId(@PathVariable Integer id) {
        AsignacionEjercicio asignacionEjercicio = asignacionEjercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La asignación de ejercicio no existe con id: " + id));
        return ResponseEntity.ok(asignacionEjercicio);
    }

    @PostMapping("/asignaciones-ejercicio")
    public AsignacionEjercicio crearAsignacionEjercicio(@RequestBody AsignacionEjercicio asignacionEjercicio) {
        return asignacionEjercicioRepository.save(asignacionEjercicio);
    }

    @PutMapping("/asignaciones-ejercicio/{id}")
    public ResponseEntity<AsignacionEjercicio> actualizarAsignacionEjercicio(@PathVariable Integer id, @RequestBody AsignacionEjercicio asignacionEjercicioDetalles) {
        AsignacionEjercicio asignacionEjercicio = asignacionEjercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La asignación de ejercicio no existe con id: " + id));

        asignacionEjercicio.setUsuario(asignacionEjercicioDetalles.getUsuario());
        asignacionEjercicio.setEjercicio(asignacionEjercicioDetalles.getEjercicio());
        asignacionEjercicio.setFechaHoraAsignacion(asignacionEjercicioDetalles.getFechaHoraAsignacion());

        AsignacionEjercicio asignacionEjercicioActualizada = asignacionEjercicioRepository.save(asignacionEjercicio);
        return ResponseEntity.ok(asignacionEjercicioActualizada);
    }

    @DeleteMapping("/asignaciones-ejercicio/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarAsignacionEjercicio(@PathVariable Integer id) {
        AsignacionEjercicio asignacionEjercicio = asignacionEjercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La asignación de ejercicio no existe con id: " + id));

        asignacionEjercicioRepository.delete(asignacionEjercicio);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
