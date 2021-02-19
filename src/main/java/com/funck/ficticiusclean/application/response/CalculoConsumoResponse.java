package com.funck.ficticiusclean.application.response;

import com.funck.ficticiusclean.domain.entities.Veiculo;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class CalculoConsumoResponse {

    private String nome;
    private String marca;
    private String modelo;
    private Integer ano;
    private BigDecimal combustivelGasto;
    private BigDecimal valorTotalGasto;

    public CalculoConsumoResponse(Veiculo veiculo, BigDecimal combustivelGasto, BigDecimal valorGasto) {
        this.nome = veiculo.getNome();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.ano = veiculo.getDataFabricacao().getYear();
        this.combustivelGasto = combustivelGasto.setScale(2, RoundingMode.HALF_UP);
        this.valorTotalGasto = valorGasto.setScale(2, RoundingMode.HALF_UP);;
    }

}
