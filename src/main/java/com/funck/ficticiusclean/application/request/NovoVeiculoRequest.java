package com.funck.ficticiusclean.application.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.funck.ficticiusclean.domain.entities.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NovoVeiculoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String modelo;

    @NotBlank
    private String marca;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @PastOrPresent
    private LocalDate dataFabricacao;

    @NotNull
    @Positive
    private BigDecimal consumoMedioCidade;

    @NotNull
    @Positive
    private BigDecimal consumoMedioRodovia;

    public Veiculo toDomainModel() {
        return Veiculo.builder()
                .nome(nome)
                .marca(marca)
                .modelo(modelo)
                .dataFabricacao(dataFabricacao)
                .consumoMedioCidade(consumoMedioCidade)
                .consumoMedioRodovia(consumoMedioRodovia)
                .build();
    }

}
