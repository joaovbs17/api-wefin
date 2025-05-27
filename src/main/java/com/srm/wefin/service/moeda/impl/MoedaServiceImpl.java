package com.srm.wefin.service.moeda.impl;

import com.srm.wefin.excecao.BaseExcecao;
import com.srm.wefin.mapper.moeda.MoedaMapper;
import com.srm.wefin.model.moeda.Moeda;
import com.srm.wefin.repository.moeda.MoedaRepository;
import com.srm.wefin.repository.produto.ProdutoRepository;
import com.srm.wefin.repository.taxacambio.TaxaCambioRepository;
import com.srm.wefin.request.moeda.MoedaRequest;
import com.srm.wefin.response.moeda.MoedaResponse;
import com.srm.wefin.service.moeda.MoedaService;
import com.srm.wefin.service.reino.ReinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class MoedaServiceImpl implements MoedaService {

    private final MoedaRepository repository;
    private final MoedaMapper mapper;
    private final ReinoService reinoService;
    private final ProdutoRepository produtoRepository;
    private final TaxaCambioRepository taxaCambioRepository;

    @Override
    public ResponseEntity<MoedaResponse> buscar(Long id){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.resposta(buscarPorId(id)));
    }

    @Override
    public ResponseEntity<List<MoedaResponse>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.resposta(repository.findAll()));
    }

    @Override
    @Transactional
    public ResponseEntity<MoedaResponse> criar(MoedaRequest requisicao) {

        requisicao.setSigla(requisicao.getSigla().toUpperCase());
        existeMoeda(requisicao.getNome(), requisicao.getSigla());
        Moeda moeda = mapper.criar(requisicao);
        moeda.setReinoOrigem(reinoService.buscarPorId(requisicao.getReinoOrigem()));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.resposta(repository.save(moeda)));
    }

    @Override
    @Transactional
    public ResponseEntity<MoedaResponse> atualizar(Long id, MoedaRequest requisicao) {

        Moeda moeda = buscarPorId(id);
        requisicao.setSigla(requisicao.getSigla().toUpperCase());
        verificarAlteracao(moeda, requisicao);
        moeda = mapper.atualizar(moeda, requisicao);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.resposta(repository.save(moeda)));
    }

    @Override
    public Moeda buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new BaseExcecao(HttpStatus.NOT_FOUND, "Moeda não encontrada."));
    }

    @Override
    public Moeda buscarPorSigla(String sigla) {
        return repository.findBySigla(sigla.toUpperCase()).orElseThrow(() -> new BaseExcecao(HttpStatus.NOT_FOUND, String.format("Moeda '%s' não encontrada", sigla)));
    }

    @Override
    @Transactional
    public void deletar(Long id){
        Moeda moeda = buscarPorId(id);
        checarAssociacaoComOutrasTabelas(id);
        repository.deleteById(moeda.getId());
    }

    private void checarAssociacaoComOutrasTabelas(Long id){
        if(produtoRepository.existsByMoedaId(id)){
            throw new BaseExcecao(HttpStatus.CONFLICT, "Essa moeda não pode ser excluída pois tem relação com produto");
        }
        if(taxaCambioRepository.existsByMoedaOrigemIdOrMoedaDestinoId(id, id)){
            throw new BaseExcecao(HttpStatus.CONFLICT, "Essa moeda não pode ser excluída pois tem relação com taxa de cambio");
        }
    }

    private void verificarAlteracao(Moeda moeda, MoedaRequest requisicao){

        String nomeAlterado = null;
        String siglaAlterada = null;
        if(!moeda.getNome().equalsIgnoreCase(requisicao.getNome())){
            nomeAlterado = requisicao.getNome();
        }
        if(!moeda.getSigla().equals(requisicao.getSigla())){
            siglaAlterada = requisicao.getNome();
        }
        existeMoeda(nomeAlterado, siglaAlterada);
    }

    private void existeMoeda(String nome, String sigla){

        Boolean existeNome = nonNull(nome) && nome.isBlank()?repository.existsByNome(nome):false;
        Boolean existeSigla = nonNull(nome) && nome.isBlank()?repository.existsByNome(nome):false;

        if(existeNome && existeSigla){
            throw new BaseExcecao(HttpStatus.CONFLICT, String.format("Já existe uma moeda cadastrada com o nome: '%s' e sigla: '%s'.", nome, sigla));
        }
        if(existeSigla){
            throw new BaseExcecao(HttpStatus.CONFLICT, String.format("Já existe uma moeda cadastrada com a sigla: '%s.'", sigla));
        }
        if(existeNome){
            throw new BaseExcecao(HttpStatus.CONFLICT, String.format("Já existe uma moeda cadastrada com o nome: '%s.'", nome));
        }
    }
}
