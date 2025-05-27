package com.srm.wefin.response.moeda;

import com.srm.wefin.response.reino.ReinoResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoedaResponse {

    private Long id;

    private String nome;

    private String sigla;

    private ReinoResponse reinoOrigem;
}
