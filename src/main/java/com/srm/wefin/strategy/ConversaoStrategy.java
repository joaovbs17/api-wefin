package com.srm.wefin.strategy;

import com.srm.wefin.strategy.impl.ConversaoStrategyParametros;

import java.math.BigDecimal;

public interface ConversaoStrategy {

    BigDecimal fazerConversao(ConversaoStrategyParametros parametros);

    void validarParametros(ConversaoStrategyParametros parametros);
}
