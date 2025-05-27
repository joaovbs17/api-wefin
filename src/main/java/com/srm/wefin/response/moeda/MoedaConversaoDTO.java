package com.srm.wefin.response.moeda;

import com.srm.wefin.response.taxacambio.TaxaCambioResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class MoedaConversaoDTO {

    private BigDecimal valorOrigem;

    private BigDecimal valorConvertido;

    private TaxaCambioResponse taxa;

    private String origemDestinoDesc;
}
