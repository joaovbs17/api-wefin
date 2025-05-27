package com.srm.wefin.service.taxacambio;

import com.srm.wefin.model.taxacambio.TaxaCambio;
import com.srm.wefin.request.taxacambio.TaxaCambioRequest;
import com.srm.wefin.response.taxacambio.TaxaCambioResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaxaCambioService {

    ResponseEntity<TaxaCambioResponse> buscar(Long id);

    ResponseEntity<TaxaCambioResponse> criar(String sigla, TaxaCambioRequest requisicao);

    ResponseEntity<TaxaCambioResponse> atualizar(Long id, TaxaCambioRequest requisicao);

    ResponseEntity<List<TaxaCambioResponse>> buscarTodosPorMoedaOrigem(String sigla);

    void deletar(Long id);

    TaxaCambio buscarPorId(Long id);

    TaxaCambio buscarPorMoedaOrigemMoedaDestino(String siglaOrigem, String siglaDestino);
}
