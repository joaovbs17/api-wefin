package com.srm.wefin.service.reino;

import com.srm.wefin.model.reino.Reino;
import com.srm.wefin.request.reino.ReinoRequest;
import com.srm.wefin.response.reino.ReinoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReinoService {

    ResponseEntity<ReinoResponse> buscar(Long id);

    ResponseEntity<ReinoResponse> criar(ReinoRequest requisicao);

    ResponseEntity<ReinoResponse> atualizar(Long id, ReinoRequest requisicao);

    ResponseEntity<List<ReinoResponse>> buscarTodos();

    void deletar(Long id);

    Reino buscarPorId(Long id);
}
