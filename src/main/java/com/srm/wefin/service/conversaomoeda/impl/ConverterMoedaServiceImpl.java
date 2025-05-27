package com.srm.wefin.service.conversaomoeda.impl;

import com.srm.wefin.enumeracao.TipoConversao;
import com.srm.wefin.factory.ConversaoFactory;
import com.srm.wefin.mapper.taxacambio.TaxaCambioMapper;
import com.srm.wefin.request.moeda.ConversaoMoedaRequest;
import com.srm.wefin.response.moeda.MoedaConversaoDTO;
import com.srm.wefin.service.conversaomoeda.ConverterMoedaService;
import com.srm.wefin.service.taxacambio.TaxaCambioService;
import com.srm.wefin.strategy.impl.ConversaoStrategyParametros;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ConverterMoedaServiceImpl implements ConverterMoedaService {

    private final ConversaoFactory conversaoFactory;
    private final TaxaCambioService taxaCambioService;
    private final TaxaCambioMapper taxaCambioMapper;

    @Override
    public ResponseEntity<MoedaConversaoDTO> calculoConversao(String sigla, ConversaoMoedaRequest requisicao){

        BigDecimal valorConvertido = conversaoFactory.calcularConversao(TipoConversao.MOEDA, montarParametro(sigla, requisicao));

        MoedaConversaoDTO dto = MoedaConversaoDTO.builder()
                .valorOrigem(requisicao.getValor())
                .valorConvertido(valorConvertido)
                .taxa(taxaCambioMapper.resposta(taxaCambioService.buscarPorMoedaOrigemMoedaDestino(sigla, requisicao.getMoedaDestinoSigla())))
                .origemDestinoDesc(String.format("%s '%s' = %s '%s'",
                        requisicao.getValor().toString(), sigla,
                        valorConvertido.toString(), requisicao.getMoedaDestinoSigla()))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    public ConversaoStrategyParametros montarParametro(String sigla, ConversaoMoedaRequest requisicao){

        return ConversaoStrategyParametros.builder()
                .moedaOrigem(sigla)
                .moedaDestino(requisicao.getMoedaDestinoSigla())
                .quatidade(BigDecimal.ONE)
                .valor(requisicao.getValor())
                .build();
    }
}
