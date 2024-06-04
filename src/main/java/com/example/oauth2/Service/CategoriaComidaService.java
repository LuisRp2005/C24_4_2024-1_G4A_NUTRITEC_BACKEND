package com.example.oauth2.Service;

import com.example.oauth2.Modelo.CategoriaComida;
import com.example.oauth2.Repository.CategoriaComidaRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaComidaService {

    @Autowired
    private CategoriaComidaRepository categoriaComidaRepository;

    public List<CategoriaComida> listarTodasLasCategorias() {
        return categoriaComidaRepository.findAll();
    }

    public CategoriaComida listarCategoriaPorId(Integer id) {
        return categoriaComidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría de comida no existe con id : " + id));
    }

    public CategoriaComida crearCategoria(CategoriaComida categoriaComida) {
        return categoriaComidaRepository.save(categoriaComida);
    }

    public CategoriaComida actualizarCategoria(Integer id, CategoriaComida categoriaRequest) {
        CategoriaComida categoriaComida = categoriaComidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría de comida no existe con id : " + id));

        categoriaComida.setNombreCategoria(categoriaRequest.getNombreCategoria());

        return categoriaComidaRepository.save(categoriaComida);
    }

    public void eliminarCategoria(Integer id) {
        CategoriaComida categoriaComida = categoriaComidaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría de comida no existe con id : " + id));
        categoriaComidaRepository.delete(categoriaComida);
    }
}
