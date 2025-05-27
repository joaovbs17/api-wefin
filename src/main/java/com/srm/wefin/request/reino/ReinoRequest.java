package com.srm.wefin.request.reino;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReinoRequest {

    @NotBlank(message = "Nome do reino não informado.")
    private String nome;
}
