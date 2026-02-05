package com.apirest.usuario.controller;

import com.apirest.usuario.business.UsuarioService;
import com.apirest.usuario.business.dto.UsuarioRequestDto;
import com.apirest.usuario.business.dto.UsuarioResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> incluirUsuario(@RequestBody UsuarioRequestDto dto){

        UsuarioResponseDto usuario_novo = service.incluirUsuario(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario_novo);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> consultarUsuarios(){

        List<UsuarioResponseDto> lista_usuarios = service.consultarUsuarios();

        return ResponseEntity.status(HttpStatus.OK).body(lista_usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> consultarUsuarioPorId(@PathVariable("id") Long id){

        UsuarioResponseDto usuario_encontrado = service.consultarUsuarioPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(usuario_encontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuarioPorId(@PathVariable("id") Long id){
        service.removerUsuarioPorId(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> alterarUsuario(@RequestBody UsuarioRequestDto dto, @PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(service.alterarUsuario(dto, id));
    }
}
