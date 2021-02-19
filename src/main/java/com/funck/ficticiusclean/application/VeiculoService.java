package com.funck.ficticiusclean.application;

import com.funck.ficticiusclean.application.request.NovoVeiculoRequest;
import com.funck.ficticiusclean.application.response.VeiculoResponse;

public interface VeiculoService {

    VeiculoResponse cadastrar(NovoVeiculoRequest veiculoRequest);

    VeiculoResponse buscar(Long id);

}
