package com.funck.ficticiusclean.application;

import com.funck.ficticiusclean.application.request.NovoVeiculoRequest;
import com.funck.ficticiusclean.application.response.VeiculoResponse;
import com.funck.ficticiusclean.domain.repositories.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class VeiculoServiceDefault implements VeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Override
    public VeiculoResponse cadastrar(NovoVeiculoRequest novoVeiculoRequest) {
        var veiculoSalvo = veiculoRepository.saveAndFlush(novoVeiculoRequest.toDomainModel());

        return new VeiculoResponse(veiculoSalvo);
    }

    @Override
    public VeiculoResponse buscar(Long id) {
        return veiculoRepository.findById(id).map(VeiculoResponse::new).orElseThrow(NoSuchElementException::new);
    }

}
