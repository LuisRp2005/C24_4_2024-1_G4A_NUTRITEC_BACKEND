package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.Ejercicio;
import com.example.oauth2.Repository.EjercicioRepository;
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
public class EjercicioController {

    @Autowired
    private EjercicioRepository EjercicioRepository;

    @GetMapping("/ejercicio")
    public List<Ejercicio> listarEjercicio(){
        return EjercicioRepository.findAll();
    }

    @PostMapping("/ejercicio")
    public Ejercicio guardarEjercicio(@RequestBody Ejercicio Ejercicio){
        return EjercicioRepository.save(Ejercicio);
    }

    @GetMapping("/ejercicio/{id}")
    public ResponseEntity<Ejercicio> listarEjercicioPorId(@PathVariable Integer id){
        Ejercicio ejercicio = EjercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ejercicio no existe id : " + id));
        return ResponseEntity.ok(ejercicio);
    }

    @PutMapping("/ejercicio/{id}")
    public ResponseEntity<Ejercicio> actualizarEjercicio(@PathVariable Integer id, @RequestBody Ejercicio ejercicioRequest){
        Ejercicio ejercicio = EjercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ejercicio no existe id : " + id));

        ejercicio.setNombre(ejercicioRequest.getNombre());
        ejercicio.setDescripcion(ejercicioRequest.getDescripcion());
        ejercicio.setNivel(ejercicioRequest.getNivel());
        ejercicio.setTipoImc(ejercicioRequest.getTipoImc());

        Ejercicio ejercicioActualizado = EjercicioRepository.save(ejercicio);
        return ResponseEntity.ok(ejercicioActualizado);
    }


    @DeleteMapping("/ejercicio/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarEjercicio(@PathVariable Integer id){
        Ejercicio ejercicio = EjercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ejercicio no existe id : " + id));

        EjercicioRepository.delete(ejercicio);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Eliminado",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
