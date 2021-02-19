package com.funck.ficticiusclean.interfaces.api;

import com.funck.ficticiusclean.application.request.CalculoConsumoRequest;
import com.funck.ficticiusclean.application.response.CalculoConsumoResponse;
import com.funck.ficticiusclean.application.CalculoPrevisaoGasto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/veiculos")
public class CalculoPrevisaoResource {

    private final CalculoPrevisaoGasto calculoPrevisaoGasto;

    // Como nenhum estado é alterado no servidor achei melhor usar um GET e receber os parâmetros via query param
    // Coloquei os objetos de request e response na camada de application, que é responsável pelos casos de uso,
    // a quem prefira colocar na mesma camada que estão os endpoints porém neste caso achei melhor ficar junto com os casos de uso
    @GetMapping("/calculo-gastos")
    public List<CalculoConsumoResponse> calcularPrevisaoGastos(@Valid CalculoConsumoRequest calculoConsumoRequest) {
        return calculoPrevisaoGasto.calcularGastos(calculoConsumoRequest);
    }

}
