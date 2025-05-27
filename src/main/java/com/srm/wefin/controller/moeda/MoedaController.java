package com.srm.wefin.controller.moeda;

import com.srm.wefin.openapi.api.ControllerMoedaApi;
import com.srm.wefin.request.moeda.ConversaoMoedaRequest;
import com.srm.wefin.request.moeda.MoedaRequest;
import com.srm.wefin.request.taxacambio.TaxaCambioRequest;
import com.srm.wefin.response.moeda.MoedaConversaoDTO;
import com.srm.wefin.response.moeda.MoedaResponse;
import com.srm.wefin.response.taxacambio.TaxaCambioResponse;
import com.srm.wefin.service.conversaomoeda.ConverterMoedaService;
import com.srm.wefin.service.moeda.MoedaService;
import com.srm.wefin.service.taxacambio.TaxaCambioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MoedaController implements ControllerMoedaApi {

    private final MoedaService service;
    private final TaxaCambioService taxaCambioService;
    private final ConverterMoedaService converterMoedaService;

    @Override
    public ResponseEntity<List<MoedaResponse>> todasMoedas(){
        return service.buscarTodos();
    }

    @Override
    public ResponseEntity<MoedaResponse> buscarMoeda(Long id){
        return service.buscar(id);
    }

    @Override
    public ResponseEntity<MoedaResponse> criarMoeda(MoedaRequest moedaRequest){
        return service.criar(moedaRequest);
    }

    @Override
    public ResponseEntity<MoedaResponse> atualizarMoeda(Long id, MoedaRequest moedaRequest){
        return service.atualizar(id, moedaRequest);
    }

    @Override
    public ResponseEntity<Void> deletarMoeda(Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<TaxaCambioResponse> criarTaxaCambio(String sigla, TaxaCambioRequest taxaCambioRequest){
        return taxaCambioService.criar(sigla, taxaCambioRequest);
    }

    @Override
    public ResponseEntity<List<TaxaCambioResponse>> buscarTodasTaxas(String sigla){
        return taxaCambioService.buscarTodosPorMoedaOrigem(sigla);
    }

    @Override
    public ResponseEntity<MoedaConversaoDTO> conversaoMoeda(String sigla, String moedaDestinoSigla, BigDecimal valor){
        ConversaoMoedaRequest requisicao = new ConversaoMoedaRequest();
        requisicao.setValor(valor);
        requisicao.setMoedaDestinoSigla(moedaDestinoSigla);
        return converterMoedaService.calculoConversao(sigla, requisicao);
    }

}
