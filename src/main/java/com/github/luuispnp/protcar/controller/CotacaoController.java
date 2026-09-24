package com.github.luuispnp.protcar.controller;

import com.github.luuispnp.protcar.dto.request.CotacaoRequest;
import com.github.luuispnp.protcar.dto.response.CotacaoResponse;
import com.github.luuispnp.protcar.service.CotacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotacoes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CotacaoController {

    private final CotacaoService service;

    @PostMapping
    public ResponseEntity<CotacaoResponse> criarCotacao(@RequestBody @Valid CotacaoRequest request) {
        CotacaoResponse novaCotacao = service.createCotacao(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCotacao);
    }

    @GetMapping
    public ResponseEntity<List<CotacaoResponse>> listarCotacoes() {
        List<CotacaoResponse> cotacoes = service.findAll();
        return ResponseEntity.ok(cotacoes);
    }
}
