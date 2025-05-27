package com.srm.wefin.model.moeda;

import com.srm.wefin.model.reino.Reino;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_moeda")
public class Moeda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sigla;

    @ManyToOne
    private Reino reinoOrigem;
}
