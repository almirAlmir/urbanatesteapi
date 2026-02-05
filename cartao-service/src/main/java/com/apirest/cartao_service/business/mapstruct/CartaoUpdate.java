package com.apirest.cartao_service.business.mapstruct;

import com.apirest.cartao_service.business.dto.CartaoRequestDto;
import com.apirest.cartao_service.infrastructure.entities.CartaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartaoUpdate {

    void updateCartao(CartaoRequestDto dto, @MappingTarget CartaoEntity entity);
}