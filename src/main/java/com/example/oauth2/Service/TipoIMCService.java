package com.example.oauth2.Service;

import com.example.oauth2.Modelo.TipoIMC;
import com.example.oauth2.Repository.TipoIMCRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoIMCService {

    @Autowired
    private TipoIMCRepository tipoIMCRepository;

    public List<TipoIMC> listarTodosLosTipos() {
        return tipoIMCRepository.findAll();
    }

    public TipoIMC listarTipoPorId(Integer id) {
        return tipoIMCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El tipo de IMC no existe con id : " + id));
    }

    public TipoIMC crearTipo(TipoIMC tipoIMC) {
        return tipoIMCRepository.save(tipoIMC);
    }

    public TipoIMC actualizarTipo(Integer id, TipoIMC tipoRequest) {
        TipoIMC tipoIMC = tipoIMCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El tipo de IMC no existe con id : " + id));

        tipoIMC.setTipoImc(tipoRequest.getTipoImc());
        tipoIMC.setDescripcionImc(tipoRequest.getDescripcionImc());

        return tipoIMCRepository.save(tipoIMC);
    }

    public void eliminarTipo(Integer id) {
        TipoIMC tipoIMC = tipoIMCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El tipo de IMC no existe con id : " + id));
        tipoIMCRepository.delete(tipoIMC);
    }
}
