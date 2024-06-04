package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.Ejercicio;
import com.example.oauth2.Service.EjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ejercicios")
@CrossOrigin("*")
public class EjercicioController {

    @Autowired
    private EjercicioService ejercicioService;

    @GetMapping
    public List<Ejercicio> listarTodosLosEjercicios() {
        return ejercicioService.listarTodosLosEjercicios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ejercicio> listarEjercicioPorId(@PathVariable Integer id) {
        Ejercicio ejercicio = ejercicioService.listarEjercicioPorId(id);
        return ResponseEntity.ok(ejercicio);
    }

    @PostMapping
    public Ejercicio crearEjercicio(@RequestBody Ejercicio ejercicio) {
        return ejercicioService.crearEjercicio(ejercicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ejercicio> actualizarEjercicio(@PathVariable Integer id, @RequestBody Ejercicio ejercicio) {
        Ejercicio actualizado = ejercicioService.actualizarEjercicio(id, ejercicio);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEjercicio(@PathVariable Integer id) {
        ejercicioService.eliminarEjercicio(id);
        return ResponseEntity.noContent().build();
    }
}
