package com.aquilesdias.challengebossabox.controller;

import com.aquilesdias.challengebossabox.domain.Usuario;
import com.aquilesdias.challengebossabox.domain.dto.CredentialDTO;
import com.aquilesdias.challengebossabox.domain.dto.LoginResponseDTO;
import com.aquilesdias.challengebossabox.domain.dto.UsuarioDTO;
import com.aquilesdias.challengebossabox.exception.DuplicateDataException;
import com.aquilesdias.challengebossabox.repositories.UsuarioRepository;
import com.aquilesdias.challengebossabox.services.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class UsuarioController {

    private final UsuarioRepository repository;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtService jwtService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid CredentialDTO credential){

        var usernamePassword = new UsernamePasswordAuthenticationToken(credential.username(), credential.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = jwtService.generatedToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok().body(new LoginResponseDTO(token));
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity register(@RequestBody @Valid UsuarioDTO usuario){

        if(repository.findByUsername(usuario.username()) != null ){
            throw new DuplicateDataException("Sorry, the username you entered already exists. " +
                    "Please choose a different username.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.password());
        Usuario user = new Usuario(usuario.username(), encryptedPassword, usuario.role());
        repository.save(user);
        return ResponseEntity.ok().build();
    }

}
