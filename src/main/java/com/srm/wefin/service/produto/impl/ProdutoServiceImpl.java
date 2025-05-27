package com.srm.wefin.service.produto.impl;

import com.srm.wefin.excecao.BaseExcecao;
import com.srm.wefin.mapper.produto.ProdutoMapper;
import com.srm.wefin.model.produto.Produto;
import com.srm.wefin.repository.produto.ProdutoRepository;
import com.srm.wefin.repository.transacao.TransacaoRepository;
import com.srm.wefin.request.produto.ProdutoRequest;
import com.srm.wefin.response.produto.ProdutoResponse;
import com.srm.wefin.service.produto.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;
    private final TransacaoRepository transacaoRepository;

    @Override
    public ResponseEntity<ProdutoResponse> buscar(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.resposta(buscarPorId(id)));
    }

    @Override
    public ResponseEntity<List<ProdutoResponse>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.resposta(repository.findAll()));
    }

    @Override
    @Transactional
    public ResponseEntity<ProdutoResponse> criar(ProdutoRequest requisicao) {
        Produto produto = mapper.criar(requisicao);
        produto = repository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.resposta(produto));
    }

    @Override
    public ResponseEntity<ProdutoResponse> atualizar(Long id, ProdutoRequest requisicao) {

        Produto produto = buscarPorId(id);
        produto = mapper.atualizar(produto, requisicao);
        produto.setId(produto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(mapper.resposta(repository.save(produto)));
    }

    @Override
    public void deletar(Long id) {
        Produto produto = buscarPorId(id);
        checarAssociacaoComOutrasTabelas(id);
        repository.deleteById(produto.getId());
    }

    private void checarAssociacaoComOutrasTabelas(Long id){
        if(transacaoRepository.existsByProdutoId(id)){
            throw new BaseExcecao(HttpStatus.CONFLICT, "Esse produto não pode ser excluído pois já foi feito transação.");
        }
    }

    @Override
    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new BaseExcecao(HttpStatus.NOT_FOUND, "Produto não encontrado."));
    }
}
