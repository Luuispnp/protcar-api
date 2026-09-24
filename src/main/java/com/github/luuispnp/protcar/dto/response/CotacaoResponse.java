package com.github.luuispnp.protcar.dto.response;

import java.time.LocalDateTime;

public record CotacaoResponse(
        Long id,
        String placa,
        String marca,
        String modelo,
        Integer anoFabricacao,
        String nomeCompleto,
        String email,
        String telefone,
        String estado,
        String cidade,
        LocalDateTime dataCriacao
) {
}
