package com.funck.ficticiusclean.application;

import com.funck.ficticiusclean.application.request.NovoVeiculoRequest;
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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

public class VeiculoServiceTest {

    @InjectMocks
    private VeiculoServiceDefault veiculoService;

    @Mock
    private VeiculoRepository veiculoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Testa o cadastro de um novo veiculo")
    @Test
    void testCadastrar() {
        // given
        var novoVeiculoRequest = novoVeiculoRequest();

        doAnswer(answer -> {
            final Veiculo veiculo = answer.getArgument(0);
            veiculo.setId(1L);
            return veiculo;
        }).when(veiculoRepository).saveAndFlush(any(Veiculo.class));

        // when
        var veiculoSalvo = veiculoService.cadastrar(novoVeiculoRequest);

        // then
        assertAll("veiculoSalvo", () -> {
            assertEquals(1L, veiculoSalvo.getId());
            assertEquals("10.50", veiculoSalvo.getConsumoMedioCidade().toString());
            assertEquals("13.00", veiculoSalvo.getConsumoMedioRodovia().toString());
            assertEquals("Chevrolet", veiculoSalvo.getMarca());
            assertEquals("Celta", veiculoSalvo.getModelo());
            assertEquals("Carro 1", veiculoSalvo.getNome());
            assertEquals(LocalDate.of(2020, 10, 10), veiculoSalvo.getDataFabricacao());
        });
    }

    @DisplayName("Deve retornar um veículo pelo id")
    @Test
    void testBuscar1() {
        // given
        var veiculo = novoVeiculoRequest().toDomainModel();
        veiculo.setId(1L);

        doReturn(Optional.of(veiculo)).when(veiculoRepository).findById(1L);

        // when
        var veiculoSalvo = veiculoService.buscar(1L);

        // then
        assertAll("veiculoSalvo", () -> {
            assertEquals(1L, veiculoSalvo.getId());
            assertEquals("10.50", veiculoSalvo.getConsumoMedioCidade().toString());
            assertEquals("13.00", veiculoSalvo.getConsumoMedioRodovia().toString());
            assertEquals("Chevrolet", veiculoSalvo.getMarca());
            assertEquals("Celta", veiculoSalvo.getModelo());
            assertEquals("Carro 1", veiculoSalvo.getNome());
            assertEquals(LocalDate.of(2020, 10, 10), veiculoSalvo.getDataFabricacao());
        });
    }

    @DisplayName("Deve lançar NoSuchElementException quando o veículo não for encontrado")
    @Test
    void testBuscar2() {
        assertThrows(NoSuchElementException.class, () -> veiculoService.buscar(1L));
    }

    private NovoVeiculoRequest novoVeiculoRequest() {
        return NovoVeiculoRequest.builder()
                .consumoMedioCidade(new BigDecimal("10.50"))
                .consumoMedioRodovia(new BigDecimal("13.00"))
                .marca("Chevrolet")
                .modelo("Celta")
                .nome("Carro 1")
                .dataFabricacao(LocalDate.of(2020, 10, 10))
                .build();
    }
}
