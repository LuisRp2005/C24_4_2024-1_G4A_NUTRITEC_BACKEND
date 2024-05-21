package com.example.oauth2.Repository;

import com.example.oauth2.Modelo.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio,Integer> {

}
