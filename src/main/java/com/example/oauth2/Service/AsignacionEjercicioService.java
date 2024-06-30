package com.example.oauth2.Service;

import com.example.oauth2.Modelo.AsignacionEjercicio;
import com.example.oauth2.Repository.AsignacionEjercicioRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignacionEjercicioService {

    @Autowired
    private AsignacionEjercicioRepository asignacionEjercicioRepository;

    public List<AsignacionEjercicio> listarTodasLasAsignaciones() {
        return asignacionEjercicioRepository.findAll();
    }

    public AsignacionEjercicio listarAsignacionPorId(Integer id) {
        return asignacionEjercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La asignación de ejercicio no existe con id : " + id));
    }

    public List<AsignacionEjercicio> listarAsignacionesPorUsuario(Integer idUsuario) {
        return asignacionEjercicioRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public AsignacionEjercicio crearAsignacion(AsignacionEjercicio asignacionEjercicio) {
        return asignacionEjercicioRepository.save(asignacionEjercicio);
    }

    public AsignacionEjercicio actualizarAsignacion(Integer id, AsignacionEjercicio asignacionRequest) {
        AsignacionEjercicio asignacionEjercicio = asignacionEjercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La asignación de ejercicio no existe con id : " + id));

        asignacionEjercicio.setUsuario(asignacionRequest.getUsuario());
        asignacionEjercicio.setEjercicio(asignacionRequest.getEjercicio());
        asignacionEjercicio.setFechaHoraAsignacion(asignacionRequest.getFechaHoraAsignacion());

        return asignacionEjercicioRepository.save(asignacionEjercicio);
    }

    public void eliminarAsignacion(Integer id) {
        AsignacionEjercicio asignacionEjercicio = asignacionEjercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La asignación de ejercicio no existe con id : " + id));
        asignacionEjercicioRepository.delete(asignacionEjercicio);
    }
}
