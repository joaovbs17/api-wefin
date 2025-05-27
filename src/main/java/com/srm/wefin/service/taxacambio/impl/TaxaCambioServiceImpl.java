package com.srm.wefin.service.taxacambio.impl;

import com.srm.wefin.excecao.BaseExcecao;
import com.srm.wefin.mapper.taxacambio.TaxaCambioMapper;
import com.srm.wefin.model.moeda.Moeda;
import com.srm.wefin.model.taxacambio.TaxaCambio;
import com.srm.wefin.repository.taxacambio.TaxaCambioRepository;
import com.srm.wefin.request.taxacambio.TaxaCambioRequest;
import com.srm.wefin.response.taxacambio.TaxaCambioResponse;
import com.srm.wefin.service.moeda.MoedaService;
import com.srm.wefin.service.taxacambio.TaxaCambioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxaCambioServiceImpl implements TaxaCambioService {

    private final TaxaCambioMapper mapper;
    private final TaxaCambioRepository repository;
    private final MoedaService moedaService;

    @Override
    public ResponseEntity<TaxaCambioResponse> buscar(Long id) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<TaxaCambioResponse> criar(String sigla, TaxaCambioRequest requisicao) {

        Moeda origem = moedaService.buscarPorSigla(sigla);
        Moeda destino = moedaService.buscarPorSigla(requisicao.getMoedaDestino());
        repository.save(mapper.criar(origem, destino, requisicao.getTaxa()));
        repository.save(mapper.criar(destino, origem, BigDecimal.ONE.divide(requisicao.getTaxa()).setScale(4, RoundingMode.HALF_EVEN)));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.resposta(buscarPorMoedaOrigemMoedaDestino(sigla, requisicao.getMoedaDestino())));
    }


    @Override
    @Transactional
    public ResponseEntity<TaxaCambioResponse> atualizar(Long id, TaxaCambioRequest requisicao) {
        return null;
    }

    @Override
    public ResponseEntity<List<TaxaCambioResponse>> buscarTodosPorMoedaOrigem(String sigla) {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.resposta(repository.findByMoedaOrigemSigla(sigla)));
    }

    @Override
    public void deletar(Long id) {

    }

    @Override
    public TaxaCambio buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BaseExcecao(HttpStatus.NOT_FOUND, "Taxa de cambio não encontrada."));
    }

    @Override
    public TaxaCambio buscarPorMoedaOrigemMoedaDestino(String siglaOrigem, String siglaDestino){
        return repository.buscarUltimaTaxa(siglaOrigem, siglaDestino)
                .orElseThrow(() -> new BaseExcecao(HttpStatus.NOT_FOUND,
                        String.format("Não foi possível encontrar a taxa da moeda origem '%s' para moeda destino '%s'", siglaOrigem, siglaDestino)));
    }
}
