package com.srm.wefin.repository.transacao;

import com.srm.wefin.model.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    Boolean existsByProdutoId(Long produtoId);
}
