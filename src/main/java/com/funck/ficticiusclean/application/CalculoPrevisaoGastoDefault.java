package com.funck.ficticiusclean.application;

import com.funck.ficticiusclean.application.request.CalculoConsumoRequest;
import com.funck.ficticiusclean.application.response.CalculoConsumoResponse;
import com.funck.ficticiusclean.domain.entities.Veiculo;
import com.funck.ficticiusclean.domain.repositories.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CalculoPrevisaoGastoDefault implements CalculoPrevisaoGasto {

    private final VeiculoRepository veiculoRepository;

    @Override
    public List<CalculoConsumoResponse> calcularGastos(CalculoConsumoRequest calculoConsumo) {
        Assert.notNull(calculoConsumo, "As informações do consumo não podem ser nulas");

        return calcularGastos(calculoConsumo, veiculoRepository.findAll());
    }

    private List<CalculoConsumoResponse> calcularGastos(CalculoConsumoRequest calculoConsumo, List<Veiculo> veiculos) {
        Assert.notNull(calculoConsumo, "As informações do consumo não podem ser nulas");
        Assert.notNull(veiculos, "Os veículos não podem ser nulos");

        return veiculos
                .stream()
                .map(veiculo -> calculoConsumo(calculoConsumo, veiculo))
                .sorted(Comparator.comparing(CalculoConsumoResponse::getValorTotalGasto))
                .collect(Collectors.toList());
    }

    private CalculoConsumoResponse calculoConsumo(CalculoConsumoRequest calculoConsumo, Veiculo veiculo) {
        Assert.notNull(calculoConsumo, "As informações do consumo não podem ser nulas");
        Assert.notNull(veiculo, "O veículo não pode ser nulo");

        final BigDecimal combustivelConsumidoCidade = calculoConsumo.getTotalPercorridoCidade().divide(veiculo.getConsumoMedioCidade(), MathContext.DECIMAL32);
        final BigDecimal combustivelConsumidoRodovia = calculoConsumo.getTotalPercorridoRodovia().divide(veiculo.getConsumoMedioRodovia(), MathContext.DECIMAL32);
        final BigDecimal totalCombustivelConsumido = combustivelConsumidoCidade.add(combustivelConsumidoRodovia);
        final BigDecimal valorGasto = totalCombustivelConsumido.multiply(calculoConsumo.getPrecoGasolina());

        return new CalculoConsumoResponse(veiculo, totalCombustivelConsumido, valorGasto);
    }

}
