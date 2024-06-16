package com.example.oauth2.Controllers;

import com.example.oauth2.Modelo.Usuario;
import com.example.oauth2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/usuario")
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @PostMapping("/usuario")
    public Usuario guardarUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //OBTENER EL TOKEN DE UN USUARIO
    @GetMapping("/token")
    public ResponseEntity<Map<String, String>> obtenerTokenYEmail() {
        // OBTENER LA AUTENTICACION ACTUAL
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // CREAR UN MAPA PARA ALMACENAR EL TOKEN Y EL EMAIL
        Map<String, String> tokenAndEmail = new HashMap<>();

        // VERIFICAR SI LA AUTENTICACION ES DE TIPO OAUTH2
        if (authentication instanceof OAuth2AuthenticationToken) {
            // OBTENER EL USUARIO AUTENTICADO
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User oauth2User = oauthToken.getPrincipal();

            // OBTENER LOS ATRIBUTOS DEL USUARIO
            String email = (String) oauth2User.getAttribute("email");

            // OBTENER EL TOKEN DE ACCESO USANDO OAuth2AuthorizedClientService
            OAuth2AccessToken accessToken = authorizedClientService.loadAuthorizedClient(
                    oauthToken.getAuthorizedClientRegistrationId(),
                    oauthToken.getName()
            ).getAccessToken();

            // AGREGAR EL TOKEN Y EL EMAIL
            tokenAndEmail.put("token", accessToken.getTokenValue());
            tokenAndEmail.put("email", email);

            // DEVOLVER EL TOKEN Y EL EMAIL
            return ResponseEntity.ok(tokenAndEmail);
        }

        // NULO SI NO ES TIPO OAUTH2
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    //VERIFICAR QUE EXISTE UN USUARIO POR SU CORREO
    @GetMapping("/verificar/{correo}")
    public Optional<Usuario> buscarUsuarioPorCorreo(@PathVariable String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    //OBTENER LOS DATOS DE UN USUARIO
    @GetMapping("/datos")
    public ResponseEntity<Usuario> listarUsuarioConectado() {
        // OBTENER LA AUTENTICACION ACTUAL
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // VERIFICAR QUE LA AUTENTICACION SEA OAUTH2
        if (authentication instanceof OAuth2AuthenticationToken) {

            // OBTENER EL USUARIO AUTENTICADO
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User oauth2User = oauthToken.getPrincipal();

            // OBTENER EL CORREO ELECTRONICO
            String email = (String) oauth2User.getAttribute("email");

            // BUSCAR EL USUARIO POR EL CORREO ELECTRONICO
            Optional<Usuario> usuario = usuarioRepository.findByCorreo(email);

            if (usuario.isPresent()) {
                return ResponseEntity.ok(usuario.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }

        // NULO SI NO ES OAUTH2
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
