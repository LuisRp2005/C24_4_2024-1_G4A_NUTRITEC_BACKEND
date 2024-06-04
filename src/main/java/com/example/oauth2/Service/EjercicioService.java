package com.example.oauth2.Service;

import com.example.oauth2.Modelo.Ejercicio;
import com.example.oauth2.Repository.EjercicioRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    public List<Ejercicio> listarTodosLosEjercicios() {
        return ejercicioRepository.findAll();
    }

    public Ejercicio listarEjercicioPorId(Integer id) {
        return ejercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ejercicio no existe con id : " + id));
    }

    public Ejercicio crearEjercicio(Ejercicio ejercicio) {
        return ejercicioRepository.save(ejercicio);
    }

    public Ejercicio actualizarEjercicio(Integer id, Ejercicio ejercicioRequest) {
        Ejercicio ejercicio = ejercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ejercicio no existe con id : " + id));

        ejercicio.setNombre(ejercicioRequest.getNombre());
        ejercicio.setDescripcion(ejercicioRequest.getDescripcion());
        ejercicio.setNivel(ejercicioRequest.getNivel());
        ejercicio.setTipoImc(ejercicioRequest.getTipoImc());

        return ejercicioRepository.save(ejercicio);
    }

    public void eliminarEjercicio(Integer id) {
        Ejercicio ejercicio = ejercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ejercicio no existe con id : " + id));
        ejercicioRepository.delete(ejercicio);
    }
}
