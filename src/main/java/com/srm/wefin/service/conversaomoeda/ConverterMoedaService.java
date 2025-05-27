package com.srm.wefin.service.conversaomoeda;

import com.srm.wefin.request.moeda.ConversaoMoedaRequest;
import com.srm.wefin.response.moeda.MoedaConversaoDTO;
import org.springframework.http.ResponseEntity;

public interface ConverterMoedaService {
    ResponseEntity<MoedaConversaoDTO> calculoConversao(String sigla, ConversaoMoedaRequest requisicao);
}
