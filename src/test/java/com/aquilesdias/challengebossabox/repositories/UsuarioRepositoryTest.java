package com.aquilesdias.challengebossabox.repositories;

import com.aquilesdias.challengebossabox.domain.Usuario;
import com.aquilesdias.challengebossabox.domain.dto.UsuarioDTO;
import com.aquilesdias.challengebossabox.domain.enums.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    EntityManager manager;

    @Test
    @DisplayName("Should get User successfully DB")
    void findByUsernameCase1() {
        String username = "Fulano";
        UsuarioDTO newUsuario = new UsuarioDTO(username, "senha123", UserRole.ADMIN);
        this.create(newUsuario);
        UserDetails result = repository.findByUsername(username);

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Should not get User successfully DB when user not exists")
    void findByUsernameCase2() {
        String username = "Fulano";
        UsuarioDTO newUsuario = new UsuarioDTO(username, "senha123", UserRole.ADMIN);
        UserDetails result = repository.findByUsername(username);

        assertThat(result).isNull();
    }

    private Usuario create(UsuarioDTO dto){
        Usuario newUsuario = new Usuario(dto);
        manager.persist(newUsuario);
        return newUsuario;
    }
}