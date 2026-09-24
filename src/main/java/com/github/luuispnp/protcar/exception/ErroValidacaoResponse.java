package com.github.luuispnp.protcar.exception;

import java.util.List;

public record ErroValidacaoResponse(
        int status,
        String erro,
        List<CampoInvalido> campos
) {
    public record CampoInvalido(String campo, String mensagem) {}
}
