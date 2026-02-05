package com.apirest.cartao_service.business.mapstruct;

import com.apirest.cartao_service.business.dto.CartaoRequestDto;
import com.apirest.cartao_service.business.dto.CartaoResponseDto;
import com.apirest.cartao_service.infrastructure.entities.CartaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartaoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "numeroCartao", ignore = true) // Gerado no service
    @Mapping(target = "status", ignore = true)       // Padrão true
    CartaoEntity paraCartaoEntity(CartaoRequestDto dto);

    CartaoResponseDto paraCartaoResponseDto(CartaoEntity entity);

    List<CartaoResponseDto> paraListaCartaoResponseDto(List<CartaoEntity> list);
}
