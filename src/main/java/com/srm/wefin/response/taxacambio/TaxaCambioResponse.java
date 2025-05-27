package com.srm.wefin.response.taxacambio;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.srm.wefin.response.moeda.MoedaResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class TaxaCambioResponse {

    private Long id;

    private MoedaResponse moedaOrigem;

    private MoedaResponse moedaDestino;

    private BigDecimal taxa;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataHora;
}
