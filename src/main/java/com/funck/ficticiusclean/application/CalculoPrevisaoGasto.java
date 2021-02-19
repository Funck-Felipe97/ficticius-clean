package com.funck.ficticiusclean.application;

import com.funck.ficticiusclean.application.request.CalculoConsumoRequest;
import com.funck.ficticiusclean.application.response.CalculoConsumoResponse;

import java.util.List;

public interface CalculoPrevisaoGasto {

    List<CalculoConsumoResponse> calcularGastos(CalculoConsumoRequest calculoConsumo);

}
