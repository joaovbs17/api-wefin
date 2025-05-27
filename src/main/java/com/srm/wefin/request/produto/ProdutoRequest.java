package com.srm.wefin.request.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProdutoRequest {

    @NotBlank(message = "Informe o nome do produto.")
    private String nome;

    @NotNull(message = "Informe a sigla da moeda de origem do produto.")
    private String moeda;
}
