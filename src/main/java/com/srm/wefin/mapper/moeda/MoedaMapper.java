package com.srm.wefin.mapper.moeda;

import com.srm.wefin.model.moeda.Moeda;
import com.srm.wefin.request.moeda.MoedaRequest;
import com.srm.wefin.response.moeda.MoedaResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MoedaMapper {

    private final ModelMapper mapper;

    public MoedaResponse resposta(Moeda moeda){
        return mapper.map(moeda, MoedaResponse.class);
    }

    public List<MoedaResponse> resposta(List<Moeda> moedas){
        return moedas.stream().map(this::resposta).collect(Collectors.toList());
    }

    public Moeda criar(MoedaRequest requisicao){
        return mapper.map(requisicao, Moeda.class);
    }

    public Moeda atualizar(Moeda moeda, MoedaRequest requisicao){
        mapper.map(requisicao, moeda);
        return moeda;
    }
}
