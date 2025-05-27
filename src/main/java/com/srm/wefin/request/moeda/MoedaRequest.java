package com.srm.wefin.request.moeda;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoedaRequest {

    @Size(max = 50, message = "O nome da moeda não pode conter mais 60 caracteres.")
    @NotBlank(message = "Nome da moeda não foi informado, por favor verfique e tente novamente.")
    private String nome;

    @Size(min = 1, max = 6, message = "A sigla deve conter pelo menos 1 ou no máximo 4 caracteres.")
    @NotBlank(message = "Sigla da moeda não foi informado, por favor verfique e tente novamente.")
    private String sigla;

    @NotNull(message = "Informe o id reino de origem.")
    private Long reinoOrigem;
}
