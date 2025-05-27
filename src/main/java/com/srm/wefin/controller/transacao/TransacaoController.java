package com.srm.wefin.controller.transacao;

import com.srm.wefin.openapi.api.ControllerTransacaoApi;
import com.srm.wefin.request.transacao.TransacaoRequest;
import com.srm.wefin.response.transacao.TransacaoResponse;
import com.srm.wefin.service.transacao.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransacaoController implements ControllerTransacaoApi {

    private final TransacaoService service;

    @Override
    public ResponseEntity<TransacaoResponse> criarTransacao(TransacaoRequest transacaoRequest){
        return service.criar(transacaoRequest);
    }
}
