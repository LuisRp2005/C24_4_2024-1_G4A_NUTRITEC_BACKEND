package com.example.oauth2.Repository;

import com.example.oauth2.Modelo.RegistroImc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroImcRepository extends JpaRepository<RegistroImc, Integer> {
}
