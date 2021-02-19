package com.funck.ficticiusclean.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Table(name = "veiculo")
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String marca;

    @NotBlank
    @Column(nullable = false)
    private String modelo;

    @PastOrPresent
    @NotNull
    @Column(nullable = false)
    private LocalDate dataFabricacao;

    @NotNull
    @Positive
    @Column(nullable = false, scale = 2)
    private BigDecimal consumoMedioCidade;

    @NotNull
    @Positive
    @Column(nullable = false, scale = 2)
    private BigDecimal consumoMedioRodovia;

}
