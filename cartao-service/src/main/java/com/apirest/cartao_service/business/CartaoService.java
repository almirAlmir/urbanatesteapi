package com.apirest.cartao_service.business;

import com.apirest.cartao_service.business.client.UsuarioClient;
import com.apirest.cartao_service.business.dto.CartaoRequestDto;
import com.apirest.cartao_service.business.dto.CartaoResponseDto;
import com.apirest.cartao_service.business.mapstruct.CartaoMapper;
import com.apirest.cartao_service.business.mapstruct.CartaoUpdate;
import com.apirest.cartao_service.infrastructure.entities.CartaoEntity;
import com.apirest.cartao_service.infrastructure.repository.CartaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import feign.FeignException;
import java.util.List;
import java.util.Random;

@Service
public class CartaoService {

    private final CartaoRepository repository;
    private final CartaoMapper mapper;
    private final CartaoUpdate cartaoUpdate;
    private final UsuarioClient usuarioClient;

    public CartaoService(CartaoRepository repository, CartaoMapper mapper, CartaoUpdate cartaoUpdate, UsuarioClient usuarioClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.cartaoUpdate = cartaoUpdate;
        this.usuarioClient = usuarioClient;
    }

    public CartaoResponseDto incluirCartao(CartaoRequestDto request) {

        try {
            usuarioClient.verificarSeUsuarioExiste(request.getUsuarioId());
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Usuário não encontrado! Não é possível criar o cartão.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao comunicar com o microsserviço de usuários.");
        }

        CartaoEntity entity = mapper.paraCartaoEntity(request);

        entity.setNumeroCartao(gerarNumeroVem());
        entity.setStatus(true);

        return mapper.paraCartaoResponseDto(repository.save(entity));
    }

    public List<CartaoResponseDto> consultarCartoes(Long usuarioId) {
        if (usuarioId != null) {
            return mapper.paraListaCartaoResponseDto(repository.findByUsuarioId(usuarioId));
        }
        return mapper.paraListaCartaoResponseDto(repository.findAll());
    }

    public CartaoResponseDto consultarCartaoPorId(Long id) {
        return mapper.paraCartaoResponseDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartão não existe")));
    }

    @Transactional
    public void removerCartaoPorId(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Cartão não encontrado! Impossível remover.");
        }
    }

    @Transactional
    public CartaoResponseDto alterarCartao(CartaoRequestDto dto, Long id) {
        CartaoEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException(
                "Cartão não encontrado! Impossível editar informação."));

        cartaoUpdate.updateCartao(dto, entity);

        return mapper.paraCartaoResponseDto(repository.save(entity));
    }

    @Transactional
    public CartaoResponseDto alterarStatus(Long id, boolean status) {
        CartaoEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException(
                "Cartão não encontrado!"));

        entity.setStatus(status);
        return mapper.paraCartaoResponseDto(repository.save(entity));
    }

    private String gerarNumeroVem() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
