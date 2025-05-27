package com.srm.wefin.request.taxacambio;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TaxaCambioRequest {

    @NotNull(message = "A sigla da moeda de destino não foi informada.")
    private String moedaDestino;

    @NotNull(message = "Taxa não informada.")
    private BigDecimal taxa;
}
