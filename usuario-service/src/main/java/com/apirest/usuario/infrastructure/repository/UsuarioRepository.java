package com.apirest.usuario.infrastructure.repository;


import com.apirest.usuario.infrastructure.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
