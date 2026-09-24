package com.github.luuispnp.protcar.controller;


import com.github.luuispnp.protcar.security.SecurityConfig;
import com.github.luuispnp.protcar.security.SecurityFilter;
import com.github.luuispnp.protcar.security.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import({SecurityConfig.class, SecurityFilter.class, TokenService.class})
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornarTokenQuandoCredenciaisForemValidas() throws Exception {
        String jsonLogin = """
                {
                    "username": "admin",
                    "password": "123456"
                }
                """;

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonLogin))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void deveRetornar401QuandoCredenciaisForemInvalidas() throws Exception {
        String jsonLoginInvalido = """
                {
                    "username": "admin",
                    "password": "senha-errada"
                }
                """;

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonLoginInvalido))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.token").doesNotExist());
    }
}
