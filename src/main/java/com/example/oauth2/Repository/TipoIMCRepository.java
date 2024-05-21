package com.example.oauth2.Repository;

import com.example.oauth2.Modelo.TipoIMC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIMCRepository extends JpaRepository<TipoIMC, Integer> {
}
