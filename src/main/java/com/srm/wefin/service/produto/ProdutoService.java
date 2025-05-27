package com.srm.wefin.service.produto;

import com.srm.wefin.model.produto.Produto;
import com.srm.wefin.request.produto.ProdutoRequest;
import com.srm.wefin.request.produto.ConversaoProdutoRequest;
import com.srm.wefin.response.produto.ProdutoConversaoDTO;
import com.srm.wefin.response.produto.ProdutoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProdutoService {

    ResponseEntity<ProdutoResponse> buscar(Long id);

    ResponseEntity<ProdutoResponse> criar(ProdutoRequest requisicao);

    ResponseEntity<ProdutoResponse> atualizar(Long id, ProdutoRequest requisicao);

    ResponseEntity<List<ProdutoResponse>> buscarTodos();

    void deletar(Long id);

    Produto buscarPorId(Long id);
}
