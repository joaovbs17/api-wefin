package com.srm.wefin.controller.reino;

import com.srm.wefin.openapi.api.ControllerReinoApi;
import com.srm.wefin.request.reino.ReinoRequest;
import com.srm.wefin.response.reino.ReinoResponse;
import com.srm.wefin.service.reino.ReinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReinoController implements ControllerReinoApi {

    private final ReinoService service;

    @Override
    public ResponseEntity<List<ReinoResponse>> todosReinos(){
        return service.buscarTodos();
    }

    @Override
    public ResponseEntity<ReinoResponse> buscarReino(Long id){
        return service.buscar(id);
    }

    @Override
    public ResponseEntity<ReinoResponse> criarReino(ReinoRequest reinoRequest){
        return service.criar(reinoRequest);
    }

    @Override
    public ResponseEntity<ReinoResponse> atualizarReino(Long id, ReinoRequest reinoRequest){
        return service.atualizar(id, reinoRequest);
    }

    @Override
    public ResponseEntity<Void> deletarReino(Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
