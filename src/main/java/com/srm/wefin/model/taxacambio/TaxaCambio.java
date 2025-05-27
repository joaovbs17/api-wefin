package com.srm.wefin.model.taxacambio;

import com.srm.wefin.model.moeda.Moeda;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_taxa_cambio")
public class TaxaCambio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Moeda moedaOrigem;

    @ManyToOne
    private Moeda moedaDestino;

    private BigDecimal taxa;

    private LocalDateTime dataHora;
}
