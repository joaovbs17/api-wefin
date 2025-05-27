package com.srm.wefin.service.transacao.impl;

import com.srm.wefin.mapper.transacao.TransacaoMapper;
import com.srm.wefin.model.transacao.Transacao;
import com.srm.wefin.repository.transacao.TransacaoRepository;
import com.srm.wefin.request.transacao.TransacaoRequest;
import com.srm.wefin.response.transacao.TransacaoResponse;
import com.srm.wefin.service.produto.ProdutoService;
import com.srm.wefin.service.taxacambio.TaxaCambioService;
import com.srm.wefin.service.transacao.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.nonNull;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoMapper mapper;
    private final ProdutoService produtoService;
    private final TaxaCambioService taxaCambioService;
    private final TransacaoRepository repository;

    @Override
    @Transactional
    public ResponseEntity<TransacaoResponse> criar(TransacaoRequest requisicao) {

        Transacao transacao = mapper.criar(requisicao);
        transacao.setTaxaCambio(taxaCambioService.buscarPorId(requisicao.getTaxaCambio()));
        transacao.setDataHora(LocalDateTime.now());
        transacao.setReinoOrigem(transacao.getTaxaCambio().getMoedaOrigem().getReinoOrigem());
        if(nonNull(requisicao.getProduto()))
            transacao.setProduto(produtoService.buscarPorId(requisicao.getProduto()));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.resposta(repository.save(transacao)));
    }

}
