package com.github.luuispnp.protcar.controller;

import com.github.luuispnp.protcar.dto.request.CotacaoRequest;
import com.github.luuispnp.protcar.dto.response.CotacaoResponse;
import com.github.luuispnp.protcar.security.SecurityConfig;
import com.github.luuispnp.protcar.security.SecurityFilter;
import com.github.luuispnp.protcar.security.TokenService;
import com.github.luuispnp.protcar.service.CotacaoService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CotacaoController.class)
@Import({SecurityConfig.class, SecurityFilter.class, TokenService.class})
class CotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CotacaoService service;

    @Test
    void deveRetornar201QuandoCriarCotacaoValida() throws Exception {
        CotacaoResponse response = new CotacaoResponse(1L, "ABC1234", "Fiat", "Uno", 2020, "João", "joao@email.com", "1199999999", "SP", "São Paulo", LocalDateTime.now());

        when(service.createCotacao(any(CotacaoRequest.class))).thenReturn(response);

        String jsonRequest = """
                {
                    "placa": "ABC1234",
                    "marca": "Fiat",
                    "modelo": "Uno",
                    "anoFabricacao": 2020,
                    "nomeCompleto": "João",
                    "email": "joao@email.com",
                    "telefone": "1199999999",
                    "estado": "SP",
                    "cidade": "São Paulo"
                }
                """;

        mockMvc.perform(post("/api/cotacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeCompleto").value("João"))
                .andExpect(jsonPath("$.placa").value("ABC1234"));
    }

    @Test
    void deveRetornar400QuandoDadosForemInvalidos() throws Exception {
        String jsonRequestInvalido = """
                {
                    "placa": "",
                    "marca": "Fiat",
                    "modelo": "Uno",
                    "anoFabricacao": 2020,
                    "nomeCompleto": "João",
                    "email": "email-invalido",
                    "telefone": "1199999999",
                    "estado": "SP",
                    "cidade": "São Paulo"
                }
                """;

        mockMvc.perform(post("/api/cotacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestInvalido))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").exists());
    }

    @Test
    void deveRetornar403QuandoTentarListarCotacoesSemToken() throws Exception {
        // Teste simples para validar se a rota GET exige autenticação
        mockMvc.perform(get("/api/cotacoes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
