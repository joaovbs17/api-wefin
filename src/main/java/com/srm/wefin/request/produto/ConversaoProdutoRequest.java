package com.srm.wefin.request.produto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ConversaoProdutoRequest {

    @NotNull(message = "Informe o valor do produto.")
    @DecimalMin(value = "0.01", inclusive = true, message = "O preço deve ser maior que zero.")
    private BigDecimal valor;

    @NotNull(message = "Informe a quantidade.")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1.")
    private Integer quantidade;

    @NotBlank(message = "Informe a sigla da moeda de destino para conversão.")
    private String moedaDestinoSigla;
}
