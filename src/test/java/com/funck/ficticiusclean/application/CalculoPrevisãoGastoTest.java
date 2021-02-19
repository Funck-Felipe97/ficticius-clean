package com.funck.ficticiusclean.application;

import com.funck.ficticiusclean.application.request.CalculoConsumoRequest;

import com.funck.ficticiusclean.application.response.CalculoConsumoResponse;
import com.funck.ficticiusclean.domain.entities.Veiculo;
import com.funck.ficticiusclean.domain.repositories.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

public class CalculoPrevisãoGastoTest {

    @InjectMocks
    private CalculoPrevisaoGastoDefault calculoPrevisaoGasto;

    @Mock
    private VeiculoRepository veiculoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Deve retornar uma lista de gastos vazia quando não existir nenhum veiculo")
    @Test
    void testCalcularGastos1() {
        // given
        CalculoConsumoRequest calculoConsumoRequest = calculoConsumoRequest(5.00, 30.51, 21.0);

        // when
        List<CalculoConsumoResponse> calculoConsumoResponses = calculoPrevisaoGasto.calcularGastos(calculoConsumoRequest);

        // then
        assertTrue(calculoConsumoResponses.isEmpty());
    }

    @DisplayName("Deve lançar IllegalArgumentException quando o calculoConsumoRequest for null")
    @Test
    void testCalcularGastos2() {
        assertThrows(IllegalArgumentException.class, () -> calculoPrevisaoGasto.calcularGastos(null));
    }

    @DisplayName("Deve lançar IllegalArgumentException quando o repositorio retornar uma lista nula")
    @Test
    void testCalcularGastos3() {
        // given
        CalculoConsumoRequest calculoConsumoRequest = calculoConsumoRequest(5.00, 30.51, 21.0);

        doReturn(null).when(veiculoRepository).findAll();

        // when then
        assertThrows(IllegalArgumentException.class, () -> calculoPrevisaoGasto.calcularGastos(null));
    }

    @DisplayName("Deve lançar IllegalArgumentException quando um dos itens da lista de veiculo for null")
    @Test
    void testCalcularGastos4() {
        // given
        CalculoConsumoRequest calculoConsumoRequest = calculoConsumoRequest(5.00, 30.51, 21.0);

        List<Veiculo> veiculos = Arrays.asList(
                veiculo(1L, 10.00, 15.00, "X", "Y", "Z", LocalDate.of(2020, 10, 10)),
                null
        );

        doReturn(veiculos).when(veiculoRepository).findAll();

        // when then
        assertThrows(IllegalArgumentException.class, () -> calculoPrevisaoGasto.calcularGastos(calculoConsumoRequest));
    }

    @DisplayName("Deve calcular os gastor previstos para cada veículo e e retornar ordenado pelo menor valor de consumo")
    @Test
    void testCalcularGastos5() {
        // given
        CalculoConsumoRequest calculoConsumoRequest = calculoConsumoRequest(5.00, 30.25, 70.13);

        final Veiculo veiculo1 = veiculo(1L, 10.00, 7.00, "Ford", "Fiesta", "Carro 1", LocalDate.of(2020, 10, 10));
        final Veiculo veiculo2 = veiculo(2L, 12.00, 21.00, "Renault", "Sandero", "Carro 2", LocalDate.of(2015, 3, 1));
        final Veiculo veiculo3 = veiculo(3L, 13.00, 14.00, "Volks", "Fusca", "Carro 3", LocalDate.of(1980, 7, 12));

        doReturn(List.of(veiculo1, veiculo2, veiculo3)).when(veiculoRepository).findAll();

        // when
        List<CalculoConsumoResponse> calculoConsumoResponses = calculoPrevisaoGasto.calcularGastos(calculoConsumoRequest);

        // then
        List<CalculoConsumoResponse> expected = List.of(
                new CalculoConsumoResponse(veiculo2, new BigDecimal(5.86), new BigDecimal(29.30)),
                new CalculoConsumoResponse(veiculo3, new BigDecimal(7.34), new BigDecimal(36.68)),
                new CalculoConsumoResponse(veiculo1, new BigDecimal(13.04), new BigDecimal(65.22))
        );

        assertEquals(3, calculoConsumoResponses.size());

        for (int i = 0; i < 3; ++i) {
            assertEquals(expected.get(i).getNome(), calculoConsumoResponses.get(i).getNome());
            assertEquals(expected.get(i).getMarca(), calculoConsumoResponses.get(i).getMarca());
            assertEquals(expected.get(i).getModelo(), calculoConsumoResponses.get(i).getModelo());
            assertEquals(expected.get(i).getAno(), calculoConsumoResponses.get(i).getAno());
            assertEquals(expected.get(i).getValorTotalGasto(), calculoConsumoResponses.get(i).getValorTotalGasto());
            assertEquals(expected.get(i).getCombustivelGasto(), calculoConsumoResponses.get(i).getCombustivelGasto());
        }
    }


    private CalculoConsumoRequest calculoConsumoRequest(Double precoGasolina, Double totalPercorridoCidade, Double totalPercorridoRodovia) {
        return CalculoConsumoRequest.builder()
                .precoGasolina(new BigDecimal(precoGasolina))
                .totalPercorridoCidade(new BigDecimal(totalPercorridoCidade))
                .totalPercorridoRodovia(new BigDecimal(totalPercorridoRodovia))
                .build();
    }

    private Veiculo veiculo(
            Long id,
            Double consumoCidade,
            Double consumoRodovia,
            String marca,
            String modelo,
            String nome,
            LocalDate dataFabricacao
    ) {
        return Veiculo.builder()
                .id(id)
                .consumoMedioCidade(new BigDecimal(consumoCidade))
                .consumoMedioRodovia(new BigDecimal(consumoRodovia))
                .marca(marca)
                .modelo(modelo)
                .nome(nome)
                .dataFabricacao(dataFabricacao)
                .build();
    }

}
