package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.TipoIMC;
import com.example.oauth2.Service.TipoIMCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-imc")
@CrossOrigin("*")
public class TipoIMCController {

    @Autowired
    private TipoIMCService tipoIMCService;

    @GetMapping
    public List<TipoIMC> listarTodosLosTipos() {
        return tipoIMCService.listarTodosLosTipos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoIMC> listarTipoPorId(@PathVariable Integer id) {
        TipoIMC tipoIMC = tipoIMCService.listarTipoPorId(id);
        return ResponseEntity.ok(tipoIMC);
    }

    @PostMapping
    public TipoIMC crearTipo(@RequestBody TipoIMC tipoIMC) {
        return tipoIMCService.crearTipo(tipoIMC);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoIMC> actualizarTipo(@PathVariable Integer id, @RequestBody TipoIMC tipoIMC) {
        TipoIMC actualizado = tipoIMCService.actualizarTipo(id, tipoIMC);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipo(@PathVariable Integer id) {
        tipoIMCService.eliminarTipo(id);
        return ResponseEntity.noContent().build();
    }
}
