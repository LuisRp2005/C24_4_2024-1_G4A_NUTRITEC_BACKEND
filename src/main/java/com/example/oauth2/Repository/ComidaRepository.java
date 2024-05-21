package com.example.oauth2.Repository;

import com.example.oauth2.Modelo.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Integer> {
}
