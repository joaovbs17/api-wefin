package com.srm.wefin.mapper.transacao;

import com.srm.wefin.model.transacao.Transacao;
import com.srm.wefin.request.transacao.TransacaoRequest;
import com.srm.wefin.response.transacao.TransacaoResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransacaoMapper {

    private final ModelMapper mapper;

    public Transacao criar(TransacaoRequest requisicao) {
        return mapper.map(requisicao, Transacao.class);
    }

    public TransacaoResponse resposta(Transacao transacao){
        return mapper.map(transacao, TransacaoResponse.class);
    }
}
