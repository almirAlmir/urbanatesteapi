package com.apirest.cartao_service.infrastructure.repository;

import com.apirest.cartao_service.infrastructure.entities.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {

    List<CartaoEntity> findByUsuarioId(Long usuarioId);
}
