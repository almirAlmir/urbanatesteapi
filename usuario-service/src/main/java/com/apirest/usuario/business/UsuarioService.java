package com.apirest.usuario.business;

import com.apirest.usuario.business.dto.UsuarioRequestDto;
import com.apirest.usuario.business.dto.UsuarioResponseDto;
import com.apirest.usuario.business.mapstruct.UsuarioMapper;
import com.apirest.usuario.business.mapstruct.UsuarioUpdate;
import com.apirest.usuario.infrastructure.entities.UsuarioEntity;
import com.apirest.usuario.infrastructure.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final UsuarioUpdate usuarioUpdate;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper, UsuarioUpdate usuarioUpdate) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioUpdate = usuarioUpdate;
    }

    public UsuarioResponseDto incluirUsuario(UsuarioRequestDto request){

        return mapper.paraUsuarioResponseDto(repository.save(mapper.paraUsuarioEntity(request)));
    }

    public List<UsuarioResponseDto> consultarUsuarios(){

        return mapper.paraListaUsuarioResponseDto(repository.findAll());
    }

    public UsuarioResponseDto consultarUsuarioPorId(Long id){

        return mapper.paraUsuarioResponseDto(repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não existe")));
    }
    @Transactional
    public void removerUsuarioPorId(Long id){

            if(repository.existsById(id)){
                repository.deleteById(id);
            }else{
                throw new RuntimeException("Usuário não encontrado! Impossível remover.");
            }
    }
    @Transactional
    public UsuarioResponseDto alterarUsuario(UsuarioRequestDto dto, Long id){

        UsuarioEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado! Impossível editar informação."));

        usuarioUpdate.updateUsuario(dto, entity);

        return mapper.paraUsuarioResponseDto(repository.save(entity));
    }
}
