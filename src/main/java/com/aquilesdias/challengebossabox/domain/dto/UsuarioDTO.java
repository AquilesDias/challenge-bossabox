package com.aquilesdias.challengebossabox.domain.dto;

import com.aquilesdias.challengebossabox.domain.enums.UserRole;

public record UsuarioDTO(String username, String password, UserRole role) {
}
