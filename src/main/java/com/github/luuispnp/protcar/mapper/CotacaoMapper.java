package com.github.luuispnp.protcar.mapper;

import com.github.luuispnp.protcar.dto.request.CotacaoRequest;
import com.github.luuispnp.protcar.dto.response.CotacaoResponse;
import com.github.luuispnp.protcar.entity.Cotacao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CotacaoMapper {

    Cotacao toEntity(CotacaoRequest request);

    CotacaoResponse toResponse(Cotacao cotacao);

}
