package com.apirest.cartao_service.controller;

import com.apirest.cartao_service.business.CartaoService;
import com.apirest.cartao_service.business.dto.CartaoRequestDto;
import com.apirest.cartao_service.business.dto.CartaoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    private final CartaoService service;

    public CartaoController(CartaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CartaoResponseDto> incluirCartao(@RequestBody CartaoRequestDto dto) {
        CartaoResponseDto novoCartao = service.incluirCartao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCartao);
    }

    @GetMapping
    public ResponseEntity<List<CartaoResponseDto>> consultarCartoes(@RequestParam(required = false) Long usuarioId) {
        List<CartaoResponseDto> lista = service.consultarCartoes(usuarioId);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoResponseDto> consultarCartaoPorId(@PathVariable("id") Long id) {
        CartaoResponseDto cartaoEncontrado = service.consultarCartaoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cartaoEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCartaoPorId(@PathVariable("id") Long id) {
        service.removerCartaoPorId(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaoResponseDto> alterarCartao(@RequestBody CartaoRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterarCartao(dto, id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CartaoResponseDto> alterarStatus(@PathVariable Long id, @RequestParam boolean ativo) {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterarStatus(id, ativo));
    }
}
