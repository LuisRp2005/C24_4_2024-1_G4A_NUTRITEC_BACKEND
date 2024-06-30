package com.example.oauth2.Service;

import com.example.oauth2.Modelo.AsignacionComida;
import com.example.oauth2.Repository.AsignacionComidaRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignacionComidaService {

    @Autowired
    private AsignacionComidaRepository asignacionComidaRepository;

    public List<AsignacionComida> listarTodasLasAsignaciones() {
        return asignacionComidaRepository.findAll();
    }

    public AsignacionComida listarAsignacionPorId(Integer id) {
        return asignacionComidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La asignación de comida no existe con id : " + id));
    }

    public List<AsignacionComida> listarAsignacionesPorUsuario(Integer idUsuario) {
        return asignacionComidaRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public AsignacionComida crearAsignacion(AsignacionComida asignacionComida) {
        return asignacionComidaRepository.save(asignacionComida);
    }

    public AsignacionComida actualizarAsignacion(Integer id, AsignacionComida asignacionRequest) {
        AsignacionComida asignacionComida = asignacionComidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La asignación de comida no existe con id : " + id));

        asignacionComida.setUsuario(asignacionRequest.getUsuario());
        asignacionComida.setComida(asignacionRequest.getComida());
        asignacionComida.setFechaHoraRegistro(asignacionRequest.getFechaHoraRegistro());

        return asignacionComidaRepository.save(asignacionComida);
    }

    public void eliminarAsignacion(Integer id) {
        AsignacionComida asignacionComida = asignacionComidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La asignación de comida no existe con id : " + id));
        asignacionComidaRepository.delete(asignacionComida);
    }
}
