package com.funck.ficticiusclean.interfaces.api;

import com.funck.ficticiusclean.application.request.CalculoConsumoRequest;
import com.funck.ficticiusclean.application.response.CalculoConsumoResponse;
import com.funck.ficticiusclean.application.CalculoPrevisaoGasto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/veiculos")
public class CalculoPrevisaoResource {

    private final CalculoPrevisaoGasto calculoPrevisaoGasto;

    @GetMapping("/calculo-gastos")
    public List<CalculoConsumoResponse> calcularPrevisaoGastos(@Valid CalculoConsumoRequest calculoConsumoRequest) {
        return calculoPrevisaoGasto.calcularGastos(calculoConsumoRequest);
    }

}
