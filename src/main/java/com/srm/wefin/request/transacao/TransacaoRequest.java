package com.srm.wefin.request.transacao;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransacaoRequest {

    private Long produto;

    private BigDecimal valorOrigem;

    private BigDecimal valorConvertido;

    private Long taxaCambio;
}
