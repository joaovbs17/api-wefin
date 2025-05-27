package com.srm.wefin.mapper.reino;

import com.srm.wefin.model.reino.Reino;
import com.srm.wefin.request.reino.ReinoRequest;
import com.srm.wefin.response.reino.ReinoResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReinoMapper {

    private final ModelMapper mapper;

    public ReinoResponse resposta(Reino reino){
        return mapper.map(reino, ReinoResponse.class);
    }

    public List<ReinoResponse> resposta(List<Reino> reino){
        return reino.stream().map(this::resposta).collect(Collectors.toList());
    }

    public Reino criar(ReinoRequest requisicao){
        return mapper.map(requisicao, Reino.class);
    }

    public Reino atualizar(Reino reino, ReinoRequest requisicao){
        mapper.map(requisicao, reino);
        return reino;
    }

}
