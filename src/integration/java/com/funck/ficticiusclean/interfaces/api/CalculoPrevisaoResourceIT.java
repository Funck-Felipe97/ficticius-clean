package com.funck.ficticiusclean.interfaces.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalculoPrevisaoResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Deve retornar 200 ok quando os parâmetros corretos forem passados")
    @Test
    void testCalcularPrevisaoGastos1() throws Exception {
        mockMvc.perform(get("/api/v1/veiculos/calculo-gastos")
                .queryParam("precoGasolina", "5.00")
                .queryParam("totalPercorridoCidade", "10.00")
                .queryParam("totalPercorridoRodovia", "10.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].nome").value("Celta"))
                .andExpect(jsonPath("$.[0].marca").value("Chevrolet"))
                .andExpect(jsonPath("$.[0].modelo").value("Hatch"))
                .andExpect(jsonPath("$.[0].ano").value("2020"))
                .andExpect(jsonPath("$.[0].combustivelGasto").value("1.83"))
                .andExpect(jsonPath("$.[0].valorTotalGasto").value("9.17"))
                .andExpect(jsonPath("$.[1].nome").value("Uno"))
                .andExpect(jsonPath("$.[1].marca").value("Chevrolet"))
                .andExpect(jsonPath("$.[1].modelo").value("Hatch"))
                .andExpect(jsonPath("$.[1].ano").value("2020"))
                .andExpect(jsonPath("$.[1].combustivelGasto").value("2.83"))
                .andExpect(jsonPath("$.[1].valorTotalGasto").value("14.17"))
                .andExpect(jsonPath("$.[2].nome").value("Fusca"))
                .andExpect(jsonPath("$.[2].marca").value("Chevrolet"))
                .andExpect(jsonPath("$.[2].modelo").value("Hatch"))
                .andExpect(jsonPath("$.[2].ano").value("2020"))
                .andExpect(jsonPath("$.[2].combustivelGasto").value("10.83"))
                .andExpect(jsonPath("$.[2].valorTotalGasto").value("54.17"))
                .andExpect(status().isOk());
    }

    @DisplayName("Deve lançar 400 Bad request quando o preço da gasolina for null")
    @Test
    void testCalcularPrevisaoGastos2() throws Exception {
        mockMvc.perform(get("/api/v1/veiculos/calculo-gastos")
                .queryParam("totalPercorridoCidade", "15.80")
                .queryParam("totalPercorridoRodovia", "30.50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Deve lançar 400 Bad request quando o preço da gasolina for negativo")
    @Test
    void testCalcularPrevisaoGastos3() throws Exception {
        mockMvc.perform(get("/api/v1/veiculos/calculo-gastos")
                .queryParam("precoGasolina", "-10.00")
                .queryParam("totalPercorridoCidade", "15.80")
                .queryParam("totalPercorridoRodovia", "30.50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Deve lançar 400 Bad request quando total percorrido na cidade for null")
    @Test
    void testCalcularPrevisaoGastos4() throws Exception {
        mockMvc.perform(get("/api/v1/veiculos/calculo-gastos")
                .queryParam("precoGasolina", "10.00")
                .queryParam("totalPercorridoRodovia", "30.50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Deve lançar 400 Bad request quando total percorrido na cidade for negativo")
    @Test
    void testCalcularPrevisaoGastos5() throws Exception {
        mockMvc.perform(get("/api/v1/veiculos/calculo-gastos")
                .queryParam("precoGasolina", "10.00")
                .queryParam("totalPercorridoCidade", "-15.80")
                .queryParam("totalPercorridoRodovia", "30.50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Deve lançar 400 Bad request quando total percorrido na rodovia for null")
    @Test
    void testCalcularPrevisaoGastos6() throws Exception {
        mockMvc.perform(get("/api/v1/veiculos/calculo-gastos")
                .queryParam("precoGasolina", "10.00")
                .queryParam("totalPercorridoCidade", "15.80")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Deve lançar 400 Bad request quando total percorrido na rodovia for negativo")
    @Test
    void testCalcularPrevisaoGastos7() throws Exception {
        mockMvc.perform(get("/api/v1/veiculos/calculo-gastos")
                .queryParam("precoGasolina", "10.00")
                .queryParam("totalPercorridoCidade", "15.80")
                .queryParam("totalPercorridoRodovia", "-30.50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
