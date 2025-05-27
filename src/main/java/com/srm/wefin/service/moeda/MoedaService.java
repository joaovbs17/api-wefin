package com.srm.wefin.service.moeda;

import com.srm.wefin.model.moeda.Moeda;
import com.srm.wefin.request.moeda.MoedaRequest;
import com.srm.wefin.response.moeda.MoedaResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MoedaService {

    ResponseEntity<MoedaResponse> buscar(Long id);

    ResponseEntity<MoedaResponse> criar(MoedaRequest requisicao);

    ResponseEntity<MoedaResponse> atualizar(Long id, MoedaRequest requisicao);

    ResponseEntity<List<MoedaResponse>> buscarTodos();

    void deletar(Long id);

    Moeda buscarPorId(Long id);

    Moeda buscarPorSigla(String sigla);
}
