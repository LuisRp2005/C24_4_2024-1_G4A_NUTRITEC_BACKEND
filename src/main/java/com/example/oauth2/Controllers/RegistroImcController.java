package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.RegistroImc;
import com.example.oauth2.Service.RegistroImcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros-imc")
@CrossOrigin("*")
public class RegistroImcController {

    @Autowired
    private RegistroImcService registroImcService;

    @GetMapping
    public List<RegistroImc> listarTodosLosRegistros() {
        return registroImcService.listarTodosLosRegistros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroImc> listarRegistroPorId(@PathVariable Integer id) {
        RegistroImc registroImc = registroImcService.listarRegistroPorId(id);
        return ResponseEntity.ok(registroImc);
    }

    @PostMapping
    public RegistroImc crearRegistro(@RequestBody RegistroImc registroImc) {
        return registroImcService.crearRegistro(registroImc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroImc> actualizarRegistro(@PathVariable Integer id, @RequestBody RegistroImc registroImc) {
        RegistroImc actualizado = registroImcService.actualizarRegistro(id, registroImc);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Integer id) {
        registroImcService.eliminarRegistro(id);
        return ResponseEntity.noContent().build();
    }
}
