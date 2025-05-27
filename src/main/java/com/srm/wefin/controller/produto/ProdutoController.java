package com.srm.wefin.controller.produto;

import com.srm.wefin.openapi.api.ControllerProdutoApi;
import com.srm.wefin.request.produto.ProdutoRequest;
import com.srm.wefin.request.produto.ConversaoProdutoRequest;
import com.srm.wefin.response.produto.ProdutoConversaoDTO;
import com.srm.wefin.response.produto.ProdutoResponse;
import com.srm.wefin.service.conversaoproduto.ConverterProdutoService;
import com.srm.wefin.service.produto.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProdutoController implements ControllerProdutoApi {

    private final ProdutoService service;
    private final ConverterProdutoService converterProdutoService;

    @Override
    public ResponseEntity<List<ProdutoResponse>> todosProdutos(){
        return service.buscarTodos();
    }

    @Override
    public ResponseEntity<ProdutoResponse> buscarProduto(Long id){
        return service.buscar(id);
    }

    @Override
    public ResponseEntity<ProdutoResponse> criarProduto(ProdutoRequest produtoRequest){
        return service.criar(produtoRequest);
    }

    @Override
    public ResponseEntity<ProdutoResponse> atualizarProduto(Long id, ProdutoRequest produtoRequest){
        return service.atualizar(id, produtoRequest);
    }

    @Override
    public ResponseEntity<Void> deletarProduto(Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProdutoConversaoDTO> conversaoProduto(Long id, String moedaDestinoSigla, BigDecimal valor, Integer quantidade){
        ConversaoProdutoRequest requisicao = new ConversaoProdutoRequest();
        requisicao.setMoedaDestinoSigla(moedaDestinoSigla);
        requisicao.setValor(valor);
        requisicao.setQuantidade(quantidade);
        return converterProdutoService.calculoConversao(id, requisicao);
    }
}
