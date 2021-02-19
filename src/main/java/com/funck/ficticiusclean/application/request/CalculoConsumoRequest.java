package com.funck.ficticiusclean.application.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CalculoConsumoRequest {

    @NotNull
    @Positive
    private BigDecimal precoGasolina;

    @NotNull
    @Positive
    private BigDecimal totalPercorridoCidade;

    @NotNull
    @Positive
    private BigDecimal totalPercorridoRodovia;


}
