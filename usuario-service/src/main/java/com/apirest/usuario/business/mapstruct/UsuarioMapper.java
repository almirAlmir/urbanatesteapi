package com.apirest.usuario.business.mapstruct;

import com.apirest.usuario.business.dto.UsuarioRequestDto;
import com.apirest.usuario.business.dto.UsuarioResponseDto;
import com.apirest.usuario.infrastructure.entities.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    UsuarioEntity paraUsuarioEntity(UsuarioRequestDto dto);

    UsuarioResponseDto paraUsuarioResponseDto(UsuarioEntity entity);

    List<UsuarioResponseDto> paraListaUsuarioResponseDto(List<UsuarioEntity> list);
}
