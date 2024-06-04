package com.example.oauth2.Service;

import com.example.oauth2.Modelo.Comida;
import com.example.oauth2.Repository.ComidaRepository;
import com.example.oauth2.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ComidaServiceTest {

    @Mock
    private ComidaRepository comidaRepository;

    @InjectMocks
    private ComidaService comidaService;

    @Test
    public void testListarTodasLasComidas() {
        Comida comida1 = new Comida();
        Comida comida2 = new Comida();
        List<Comida> comidas = Arrays.asList(comida1, comida2);

        when(comidaRepository.findAll()).thenReturn(comidas);

        List<Comida> result = comidaService.listarTodasLasComidas();
        assertEquals(2, result.size());
    }

    @Test
    public void testListarComidaPorId() {
        Comida comida = new Comida();
        comida.setIdComida(1);

        when(comidaRepository.findById(1)).thenReturn(Optional.of(comida));

        Comida result = comidaService.listarComidaPorId(1);
        assertEquals(1, result.getIdComida());
    }

    @Test
    public void testListarComidaPorId_NotFound() {
        when(comidaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            comidaService.listarComidaPorId(1);
        });
    }

    @Test
    public void testCrearComida() {
        Comida comida = new Comida();

        when(comidaRepository.save(comida)).thenReturn(comida);

        Comida result = comidaService.crearComida(comida);
        assertNotNull(result);
    }

    @Test
    public void testActualizarComida() {
        Comida comida = new Comida();
        comida.setIdComida(1);

        Comida updatedComida = new Comida();
        updatedComida.setNombreComida("Nuevo Nombre");

        when(comidaRepository.findById(1)).thenReturn(Optional.of(comida));
        when(comidaRepository.save(comida)).thenReturn(comida);

        Comida result = comidaService.actualizarComida(1, updatedComida);
        assertEquals("Nuevo Nombre", result.getNombreComida());
    }

    @Test
    public void testEliminarComida() {
        Comida comida = new Comida();
        comida.setIdComida(1);

        when(comidaRepository.findById(1)).thenReturn(Optional.of(comida));

        comidaService.eliminarComida(1);
        verify(comidaRepository, times(1)).delete(comida);
    }
}
