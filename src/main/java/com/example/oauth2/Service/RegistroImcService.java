package com.example.oauth2.Service;

import com.example.oauth2.Modelo.RegistroImc;
import com.example.oauth2.Repository.RegistroImcRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroImcService {

    @Autowired
    private RegistroImcRepository registroImcRepository;

    public List<RegistroImc> listarTodosLosRegistros() {
        return registroImcRepository.findAll();
    }

    public RegistroImc listarRegistroPorId(Integer id) {
        return registroImcRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El registro de IMC no existe con id : " + id));
    }

    public RegistroImc crearRegistro(RegistroImc registroImc) {
        return registroImcRepository.save(registroImc);
    }

    public RegistroImc actualizarRegistro(Integer id, RegistroImc registroRequest) {
        RegistroImc registroImc = registroImcRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El registro de IMC no existe con id : " + id));

        registroImc.setUsuario(registroRequest.getUsuario());
        registroImc.setTipoImc(registroRequest.getTipoImc());
        registroImc.setFechaHoraRegistro(registroRequest.getFechaHoraRegistro());

        return registroImcRepository.save(registroImc);
    }

    public void eliminarRegistro(Integer id) {
        RegistroImc registroImc = registroImcRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El registro de IMC no existe con id : " + id));
        registroImcRepository.delete(registroImc);
    }
}
