package com.srm.wefin.request.moeda;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ConversaoMoedaRequest {

    @NotNull(message = "Informe o valor do produto.")
    @DecimalMin(value = "0.01", inclusive = true, message = "O valor deve ser maior que zero.")
    private BigDecimal valor;

    @NotBlank(message = "Informe a sigla da moeda de destino para conversão.")
    private String moedaDestinoSigla;
}
