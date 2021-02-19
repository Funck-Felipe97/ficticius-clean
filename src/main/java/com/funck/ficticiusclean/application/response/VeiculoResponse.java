package com.funck.ficticiusclean.application.response;

import com.funck.ficticiusclean.domain.entities.Veiculo;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@EqualsAndHashCode(of = "id")
@Getter
public class VeiculoResponse {

    private Long id;
    private String nome;
    private String modelo;
    private String marca;
    private LocalDate dataFabricacao;
    private BigDecimal consumoMedioCidade;
    private BigDecimal consumoMedioRodovia;

    public VeiculoResponse(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.nome = veiculo.getNome();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.dataFabricacao = veiculo.getDataFabricacao();
        this.consumoMedioCidade = veiculo.getConsumoMedioCidade().setScale(2, RoundingMode.CEILING);
        this.consumoMedioRodovia = veiculo.getConsumoMedioRodovia().setScale(2, RoundingMode.CEILING);
    }

}
