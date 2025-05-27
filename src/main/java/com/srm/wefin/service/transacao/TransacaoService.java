package com.srm.wefin.service.transacao;

import com.srm.wefin.request.transacao.TransacaoRequest;
import com.srm.wefin.response.transacao.TransacaoResponse;
import org.springframework.http.ResponseEntity;

public interface TransacaoService {

    ResponseEntity<TransacaoResponse> criar(TransacaoRequest requisicao);
}
