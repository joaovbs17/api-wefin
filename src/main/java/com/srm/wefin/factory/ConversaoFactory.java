package com.srm.wefin.factory;

import com.srm.wefin.enumeracao.TipoConversao;
import com.srm.wefin.excecao.BaseExcecao;
import com.srm.wefin.strategy.ConversaoStrategy;
import com.srm.wefin.strategy.impl.moeda.ConversaoMoedaStrategy;
import com.srm.wefin.strategy.impl.produto.ConversaoProdutoStrategy;
import com.srm.wefin.strategy.impl.ConversaoStrategyParametros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConversaoFactory {

    private final Map<TipoConversao, ConversaoStrategy> estrategiaMap;

    @Autowired
    public ConversaoFactory(List<ConversaoStrategy> estrategias) {
        this.estrategiaMap = new HashMap<>();
        this.estrategiaMap.put(TipoConversao.PRODUTO, pegarConversaoStrategy(estrategias, ConversaoProdutoStrategy.class));
        this.estrategiaMap.put(TipoConversao.MOEDA, pegarConversaoStrategy(estrategias, ConversaoMoedaStrategy.class));
    }

    public BigDecimal calcularConversao(TipoConversao tipo, ConversaoStrategyParametros parametros) {
        return this.estrategiaMap.get(tipo).fazerConversao(parametros);
    }

    private ConversaoStrategy pegarConversaoStrategy(List<ConversaoStrategy> estrategias, Class<?> classe){
        return estrategias.stream()
                .filter(classe::isInstance)
                .findFirst()
                .orElseThrow(() -> new BaseExcecao(HttpStatus.BAD_REQUEST, "Não foi possível fazer a conversão."));
    }
}
