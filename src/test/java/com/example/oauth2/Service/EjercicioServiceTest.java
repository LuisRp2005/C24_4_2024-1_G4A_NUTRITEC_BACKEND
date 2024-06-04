package com.example.oauth2.Service;

import com.example.oauth2.Modelo.Ejercicio;
import com.example.oauth2.Repository.EjercicioRepository;
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
public class EjercicioServiceTest {

    @Mock
    private EjercicioRepository ejercicioRepository;

    @InjectMocks
    private EjercicioService ejercicioService;

    @Test
    public void testListarTodosLosEjercicios() {
        Ejercicio ejercicio1 = new Ejercicio();
        Ejercicio ejercicio2 = new Ejercicio();
        List<Ejercicio> ejercicios = Arrays.asList(ejercicio1, ejercicio2);

        when(ejercicioRepository.findAll()).thenReturn(ejercicios);

        List<Ejercicio> result = ejercicioService.listarTodosLosEjercicios();
        assertEquals(2, result.size());
    }

    @Test
    public void testListarEjercicioPorId() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setId(1);

        when(ejercicioRepository.findById(1)).thenReturn(Optional.of(ejercicio));

        Ejercicio result = ejercicioService.listarEjercicioPorId(1);
        assertEquals(1, result.getId());
    }

    @Test
    public void testListarEjercicioPorId_NotFound() {
        when(ejercicioRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            ejercicioService.listarEjercicioPorId(1);
        });
    }

    @Test
    public void testCrearEjercicio() {
        Ejercicio ejercicio = new Ejercicio();

        when(ejercicioRepository.save(ejercicio)).thenReturn(ejercicio);

        Ejercicio result = ejercicioService.crearEjercicio(ejercicio);
        assertNotNull(result);
    }

    @Test
    public void testActualizarEjercicio() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setId(1);

        Ejercicio updatedEjercicio = new Ejercicio();
        updatedEjercicio.setNombre("Nuevo Nombre");

        when(ejercicioRepository.findById(1)).thenReturn(Optional.of(ejercicio));
        when(ejercicioRepository.save(ejercicio)).thenReturn(ejercicio);

        Ejercicio result = ejercicioService.actualizarEjercicio(1, updatedEjercicio);
        assertEquals("Nuevo Nombre", result.getNombre());
    }

    @Test
    public void testEliminarEjercicio() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setId(1);

        when(ejercicioRepository.findById(1)).thenReturn(Optional.of(ejercicio));

        ejercicioService.eliminarEjercicio(1);
        verify(ejercicioRepository, times(1)).delete(ejercicio);
    }
}
