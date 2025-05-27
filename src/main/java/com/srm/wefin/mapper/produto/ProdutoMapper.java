package com.srm.wefin.mapper.produto;

import com.srm.wefin.mapper.moeda.MoedaMapper;
import com.srm.wefin.model.produto.Produto;
import com.srm.wefin.request.produto.ProdutoRequest;
import com.srm.wefin.response.produto.ProdutoResponse;
import com.srm.wefin.service.moeda.MoedaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProdutoMapper {

    private final ModelMapper mapper;
    private final MoedaMapper moedaMapper;
    private final MoedaService moedaService;

    public ProdutoResponse resposta(Produto produto){
        ProdutoResponse resposta = mapper.map(produto, ProdutoResponse.class);
        resposta.setMoeda(moedaMapper.resposta(produto.getMoeda()));
        return mapper.map(produto, ProdutoResponse.class);
    }

    public List<ProdutoResponse> resposta(List<Produto> produtos){
        return produtos.stream().map(this::resposta).collect(Collectors.toList());
    }

    public Produto criar(ProdutoRequest requisicao){
        Produto produto =  mapper.map(requisicao, Produto.class);
        produto.setMoeda(moedaService.buscarPorSigla(requisicao.getMoeda()));
        return produto;
    }

    public Produto atualizar(Produto produto, ProdutoRequest requisicao){
        mapper.map(requisicao, produto);
        produto.setMoeda(moedaService.buscarPorSigla(requisicao.getMoeda()));
        return produto;
    }

}
