package com.funck.ficticiusclean.interfaces.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funck.ficticiusclean.application.request.NovoVeiculoRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VeiculoResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @DisplayName("Deve salvar um novo veículo e retornar 201")
    @Test
    void testNovoVeiculo() throws Exception {
        mockMvc.perform(post("/api/v1/veiculos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(novoVeiculoRequest())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("4"))
                .andExpect(jsonPath("$.consumoMedioCidade").value("10.53"))
                .andExpect(jsonPath("$.consumoMedioRodovia").value("13.18"))
                .andExpect(jsonPath("$.marca").value("Chevrolet"))
                .andExpect(jsonPath("$.modelo").value("Celta"))
                .andExpect(jsonPath("$.dataFabricacao").value("2020-10-10"));

    }

    @DisplayName("Deve buscar um veículo pelo id e retornar 200")
    @Test
    void testBuscar() throws Exception {
        mockMvc.perform(get("/api/v1/veiculos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.consumoMedioCidade").value("10.0"))
                .andExpect(jsonPath("$.consumoMedioRodovia").value("12.0"))
                .andExpect(jsonPath("$.marca").value("Chevrolet"))
                .andExpect(jsonPath("$.modelo").value("Hatch"))
                .andExpect(jsonPath("$.nome").value("Celta"))
                .andExpect(jsonPath("$.dataFabricacao").value("2020-11-20"));
    }

    private NovoVeiculoRequest novoVeiculoRequest() {
        return NovoVeiculoRequest.builder()
                .consumoMedioCidade(new BigDecimal("10.53"))
                .consumoMedioRodovia(new BigDecimal("13.18"))
                .marca("Chevrolet")
                .modelo("Celta")
                .nome("Carro 1")
                .dataFabricacao(LocalDate.of(2020, 10, 10))
                .build();
    }

}
