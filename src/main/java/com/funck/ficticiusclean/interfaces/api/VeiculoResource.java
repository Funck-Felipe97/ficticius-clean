package com.funck.ficticiusclean.interfaces.api;

import com.funck.ficticiusclean.application.VeiculoService;
import com.funck.ficticiusclean.application.request.NovoVeiculoRequest;
import com.funck.ficticiusclean.application.response.VeiculoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/veiculos")
public class VeiculoResource {

    private final VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<VeiculoResponse> novoVeiculo(@RequestBody @Valid final NovoVeiculoRequest novoVeiculoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.cadastrar(novoVeiculoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponse> buscar(@PathVariable final Long id) {
        return ResponseEntity.ok(veiculoService.buscar(id));
    }

}
