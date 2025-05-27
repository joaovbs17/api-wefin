package com.srm.wefin.service.conversaoproduto;

import com.srm.wefin.request.produto.ConversaoProdutoRequest;
import com.srm.wefin.response.produto.ProdutoConversaoDTO;
import org.springframework.http.ResponseEntity;

public interface ConverterProdutoService {

    ResponseEntity<ProdutoConversaoDTO> calculoConversao(Long id, ConversaoProdutoRequest requisicao);
}
