package com.github.luuispnp.protcar.service;

import com.github.luuispnp.protcar.dto.request.CotacaoRequest;
import com.github.luuispnp.protcar.dto.response.CotacaoResponse;
import com.github.luuispnp.protcar.entity.Cotacao;
import com.github.luuispnp.protcar.mapper.CotacaoMapper;
import com.github.luuispnp.protcar.repository.CotacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CotacaoServiceTest {

    @Mock
    private CotacaoRepository cotacaoRepository;

    @Mock
    private CotacaoMapper cotacaoMapper;

    @InjectMocks
    private CotacaoService cotacaoService;

    @Test
    void deveSalvarCotacaoComSucesso() {
        // Arrange (Preparação)
        CotacaoRequest request = new CotacaoRequest("ABC1234", "Fiat", "Uno", 2020, "João", "joao@email.com", "1199999999", "SP", "São Paulo");
        Cotacao cotacao = new Cotacao(1L, "ABC1234", "Fiat", "Uno", 2020, "João", "joao@email.com", "1199999999", "SP", "São Paulo", LocalDateTime.now());
        CotacaoResponse response = new CotacaoResponse(1L, "ABC1234", "Fiat", "Uno", 2020, "João", "joao@email.com", "1199999999", "SP", "São Paulo", LocalDateTime.now());

        when(cotacaoMapper.toEntity(request)).thenReturn(cotacao);
        when(cotacaoRepository.save(any(Cotacao.class))).thenReturn(cotacao);
        when(cotacaoMapper.toResponse(cotacao)).thenReturn(response);

        // Act (Ação)
        CotacaoResponse resultado = cotacaoService.createCotacao(request);

        // Assert (Verificação)
        assertNotNull(resultado);
        assertEquals("ABC1234", resultado.placa());
        assertEquals("João", resultado.nomeCompleto());
        verify(cotacaoRepository, times(1)).save(any(Cotacao.class));
    }
}
