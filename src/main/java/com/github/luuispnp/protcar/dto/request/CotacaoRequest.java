package com.github.luuispnp.protcar.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CotacaoRequest (
        @NotBlank(message = "A placa é obrigatória") @Size(max = 8) String placa,
        @NotBlank(message = "A marca é obrigatória") String marca,
        @NotBlank(message = "O modelo é obrigatório") String modelo,
        @NotNull(message = "O ano é obrigatório") Integer anoFabricacao,
        @NotBlank(message = "O nome completo é obrigatório") String nomeCompleto,
        @NotBlank(message = "O email é obrigatório") @Email(message = "Email inválido") String email,
        @NotBlank(message = "O telefone é obrigatório") String telefone,
        @NotBlank(message = "O estado é obrigatório") String estado,
        @NotBlank(message = "A cidade é obrigatória") String cidade
) {}
