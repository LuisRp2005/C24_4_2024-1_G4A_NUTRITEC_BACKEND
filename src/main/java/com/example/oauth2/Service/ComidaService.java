package com.example.oauth2.Service;

import com.example.oauth2.Modelo.Comida;
import com.example.oauth2.Repository.ComidaRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComidaService {

    @Autowired
    private ComidaRepository comidaRepository;

    public List<Comida> listarTodasLasComidas() {
        return comidaRepository.findAll();
    }

    public Comida listarComidaPorId(Integer id) {
        return comidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La comida no existe con id : " + id));
    }

    public Comida crearComida(Comida comida) {
        return comidaRepository.save(comida);
    }

    public Comida actualizarComida(Integer id, Comida comidaRequest) {
        Comida comida = comidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La comida no existe con id : " + id));

        comida.setNombreComida(comidaRequest.getNombreComida());
        comida.setCategoriaComida(comidaRequest.getCategoriaComida());
        comida.setCalorias(comidaRequest.getCalorias());

        return comidaRepository.save(comida);
    }

    public void eliminarComida(Integer id) {
        Comida comida = comidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La comida no existe con id : " + id));
        comidaRepository.delete(comida);
    }
}
