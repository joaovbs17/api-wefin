package com.srm.wefin.service.reino.impl;

import com.srm.wefin.excecao.BaseExcecao;
import com.srm.wefin.mapper.reino.ReinoMapper;
import com.srm.wefin.model.reino.Reino;
import com.srm.wefin.repository.moeda.MoedaRepository;
import com.srm.wefin.repository.reino.ReinoRepository;
import com.srm.wefin.request.reino.ReinoRequest;
import com.srm.wefin.response.reino.ReinoResponse;
import com.srm.wefin.service.reino.ReinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReinoServiceImpl implements ReinoService {

    private final ReinoRepository repository;
    private final ReinoMapper mapper;
    private final MoedaRepository moedaRepository;

    @Override
    public ResponseEntity<ReinoResponse> buscar(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.resposta(buscarPorId(id)));
    }

    @Override
    public ResponseEntity<ReinoResponse> criar(ReinoRequest requisicao) {
        Reino reino = mapper.criar(requisicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.resposta(repository.save(reino)));
    }

    @Override
    public ResponseEntity<ReinoResponse> atualizar(Long id, ReinoRequest requisicao) {

        Reino reino = buscarPorId(id);
        reino = mapper.atualizar(reino, requisicao);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.resposta(repository.save(reino)));
    }

    @Override
    public ResponseEntity<List<ReinoResponse>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.resposta(repository.findAll()));
    }

    @Override
    public void deletar(Long id) {
        Reino reino = buscarPorId(id);
        checarAssociacaoComMoeda(id);
        repository.deleteById(reino.getId());
    }

    private void checarAssociacaoComMoeda(Long id){
        if(moedaRepository.existsByReinoOrigemId(id)){
            throw new BaseExcecao(HttpStatus.CONFLICT, "Esse Reino não pode ser excluído pois possui relação com moeda.");
        }
    }

    @Override
    public Reino buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new BaseExcecao(HttpStatus.NOT_FOUND, "Reino não encontrado."));
    }
}
