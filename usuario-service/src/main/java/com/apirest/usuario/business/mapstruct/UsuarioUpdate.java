package com.apirest.usuario.business.mapstruct;

import com.apirest.usuario.business.dto.UsuarioRequestDto;
import com.apirest.usuario.infrastructure.entities.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioUpdate {

    void updateUsuario(UsuarioRequestDto dto, @MappingTarget UsuarioEntity entity);
}
