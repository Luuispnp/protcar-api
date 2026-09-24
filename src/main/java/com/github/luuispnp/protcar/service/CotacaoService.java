package com.github.luuispnp.protcar.service;

import com.github.luuispnp.protcar.mapper.CotacaoMapper;
import com.github.luuispnp.protcar.dto.request.CotacaoRequest;
import com.github.luuispnp.protcar.dto.response.CotacaoResponse;
import com.github.luuispnp.protcar.entity.Cotacao;
import com.github.luuispnp.protcar.repository.CotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CotacaoService {

    private final CotacaoRepository cotacaoRepository;

    private final CotacaoMapper cotacaoMapper;

    @Transactional
    public CotacaoResponse createCotacao(CotacaoRequest request) {
        Cotacao cotacao = cotacaoMapper.toEntity(request);
        Cotacao cotacaoSalva = cotacaoRepository.save(cotacao);
        return cotacaoMapper.toResponse(cotacaoSalva);
    }

    public List<CotacaoResponse> findAll() {
        List<Cotacao> cotacoes = cotacaoRepository.findAll(Sort.by(Sort.Direction.DESC, "dataCriacao"));
        return cotacoes.stream()
                .map(cotacaoMapper::toResponse)
                .collect(Collectors.toList());
    }
}
