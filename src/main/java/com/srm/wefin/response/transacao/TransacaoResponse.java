package com.srm.wefin.response.transacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.srm.wefin.response.produto.ProdutoResponse;
import com.srm.wefin.response.reino.ReinoResponse;
import com.srm.wefin.response.taxacambio.TaxaCambioResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransacaoResponse {

    private Long id;

    private ProdutoResponse produto;

    private BigDecimal valorOrigem;

    private BigDecimal valorConvertido;

    private TaxaCambioResponse taxaCambio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataHora;

    private ReinoResponse reinoOrigem;

}
