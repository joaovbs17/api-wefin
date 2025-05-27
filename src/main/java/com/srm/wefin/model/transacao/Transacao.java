package com.srm.wefin.model.transacao;

import com.srm.wefin.model.produto.Produto;
import com.srm.wefin.model.reino.Reino;
import com.srm.wefin.model.taxacambio.TaxaCambio;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_transacao")
public class Transacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    private BigDecimal valorOrigem;

    private BigDecimal valorConvertido;

    @ManyToOne
    private TaxaCambio taxaCambio;

    private LocalDateTime dataHora;

    @ManyToOne
    private Reino reinoOrigem;
}
