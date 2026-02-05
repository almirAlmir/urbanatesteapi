package com.apirest.cartao_service.business.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-service", path = "/usuario")
public interface UsuarioClient {

    @GetMapping("/{id}")
    void verificarSeUsuarioExiste(@PathVariable("id") Long id);
}
