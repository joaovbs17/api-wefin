package com.srm.wefin.response.produto;

import com.srm.wefin.response.moeda.MoedaResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponse {

    private Long id;

    private String nome;

    private MoedaResponse moeda;
}
