package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.TipoIMC;
import com.example.oauth2.Repository.TipoIMCRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TipoIMCController {

    @Autowired
    private TipoIMCRepository tipoIMCRepository;

    @GetMapping("/tiposimc")
    public List<TipoIMC> listarTodosLosTiposIMC() {
        return tipoIMCRepository.findAll();
    }

    @GetMapping("/tiposimc/{id}")
    public ResponseEntity<TipoIMC> obtenerTipoIMCPorId(@PathVariable Integer id) {
        TipoIMC tipoIMC = tipoIMCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El tipo de IMC no existe con id: " + id));
        return ResponseEntity.ok(tipoIMC);
    }

    @PostMapping("/tiposimc")
    public TipoIMC crearTipoIMC(@RequestBody TipoIMC tipoIMC) {
        return tipoIMCRepository.save(tipoIMC);
    }

    @PutMapping("/tiposimc/{id}")
    public ResponseEntity<TipoIMC> actualizarTipoIMC(@PathVariable Integer id, @RequestBody TipoIMC tipoIMCDetalles) {
        TipoIMC tipoIMC = tipoIMCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El tipo de IMC no existe con id: " + id));

        tipoIMC.setTipoImc(tipoIMCDetalles.getTipoImc());
        tipoIMC.setDescripcionImc(tipoIMCDetalles.getDescripcionImc());

        TipoIMC tipoIMCActualizado = tipoIMCRepository.save(tipoIMC);
        return ResponseEntity.ok(tipoIMCActualizado);
    }

    @DeleteMapping("/tiposimc/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarTipoIMC(@PathVariable Integer id) {
        TipoIMC tipoIMC = tipoIMCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El tipo de IMC no existe con id: " + id));

        tipoIMCRepository.delete(tipoIMC);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
