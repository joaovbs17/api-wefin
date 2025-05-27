package com.srm.wefin.repository.produto;

import com.srm.wefin.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Boolean existsByMoedaId(Long moedaId);
}
