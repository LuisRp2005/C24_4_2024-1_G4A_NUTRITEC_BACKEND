package com.example.oauth2.Service;

import com.example.oauth2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository userRepository;

    public boolean userExists(String email) {
        return userRepository.findByCorreo(email).isPresent();
    }
}