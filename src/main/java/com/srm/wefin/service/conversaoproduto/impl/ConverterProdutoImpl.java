package com.srm.wefin.service.conversaoproduto.impl;

import com.srm.wefin.enumeracao.TipoConversao;
import com.srm.wefin.factory.ConversaoFactory;
import com.srm.wefin.mapper.produto.ProdutoMapper;
import com.srm.wefin.mapper.taxacambio.TaxaCambioMapper;
import com.srm.wefin.model.produto.Produto;
import com.srm.wefin.request.produto.ConversaoProdutoRequest;
import com.srm.wefin.response.produto.ProdutoConversaoDTO;
import com.srm.wefin.service.conversaoproduto.ConverterProdutoService;
import com.srm.wefin.service.produto.ProdutoService;
import com.srm.wefin.service.taxacambio.TaxaCambioService;
import com.srm.wefin.strategy.impl.ConversaoStrategyParametros;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ConverterProdutoImpl implements ConverterProdutoService {

    private final ConversaoFactory conversaoFactory;
    private final TaxaCambioService taxaCambioService;
    private final TaxaCambioMapper taxaCambioMapper;
    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    @Override
    public ResponseEntity<ProdutoConversaoDTO> calculoConversao(Long id, ConversaoProdutoRequest requisicao){

        Produto produto = produtoService.buscarPorId(id);
        BigDecimal valorOrigem = requisicao.getValor().multiply(BigDecimal.valueOf(requisicao.getQuantidade()));
        BigDecimal valorConvertido = conversaoFactory.calcularConversao(TipoConversao.PRODUTO, montarParametro(produto, requisicao));

        ProdutoConversaoDTO dto = ProdutoConversaoDTO.builder()
                .produto(produtoMapper.resposta(produto))
                .valorOrigem(valorOrigem)
                .valorConvertido(valorConvertido)
                .taxa(taxaCambioMapper.resposta(taxaCambioService.buscarPorMoedaOrigemMoedaDestino(produto.getMoeda().getSigla(), requisicao.getMoedaDestinoSigla())))
                .origemDestinoDesc(String.format("%s '%s' = %s '%s'",
                        valorOrigem, produto.getMoeda().getSigla(),
                        valorConvertido.toString(), requisicao.getMoedaDestinoSigla()))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    public ConversaoStrategyParametros montarParametro(Produto produto, ConversaoProdutoRequest requisicao){

        return ConversaoStrategyParametros.builder()
                .produto(produto)
                .moedaOrigem(produto.getMoeda().getSigla())
                .moedaDestino(requisicao.getMoedaDestinoSigla())
                .quatidade(BigDecimal.valueOf(requisicao.getQuantidade()))
                .valor(requisicao.getValor())
                .build();
    }
}
