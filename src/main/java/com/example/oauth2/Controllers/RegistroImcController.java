package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.RegistroImc;
import com.example.oauth2.Repository.RegistroImcRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RegistroImcController {

    @Autowired
    private RegistroImcRepository registroImcRepository;

    @GetMapping("/registrosImc")
    public List<RegistroImc> listarTodosLosRegistrosImc() {
        return registroImcRepository.findAll();
    }

    @GetMapping("/registrosImc/{id}")
    public ResponseEntity<RegistroImc> listarRegistroImcPorId(@PathVariable Integer id) {
        RegistroImc registroImc = registroImcRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El registro IMC no existe con id : " + id));
        return ResponseEntity.ok(registroImc);
    }

    @PostMapping("/registrosImc")
    public RegistroImc crearRegistroImc(@RequestBody RegistroImc registroImc) {
        return registroImcRepository.save(registroImc);
    }

    @PutMapping("/registrosImc/{id}")
    public ResponseEntity<RegistroImc> actualizarRegistroImc(@PathVariable Integer id, @RequestBody RegistroImc registroImcRequest) {
        RegistroImc registroImc = registroImcRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El registro IMC no existe con id : " + id));

        registroImc.setUsuario(registroImcRequest.getUsuario());
        registroImc.setTipoImc(registroImcRequest.getTipoImc());
        registroImc.setFechaHoraRegistro(registroImcRequest.getFechaHoraRegistro());

        RegistroImc registroImcActualizado = registroImcRepository.save(registroImc);
        return ResponseEntity.ok(registroImcActualizado);
    }

    @DeleteMapping("/registrosImc/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarRegistroImc(@PathVariable Integer id) {
        RegistroImc registroImc = registroImcRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El registro IMC no existe con id : " + id));

        registroImcRepository.delete(registroImc);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}