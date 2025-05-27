package com.srm.wefin.repository.reino;

import com.srm.wefin.model.reino.Reino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReinoRepository extends JpaRepository<Reino, Long> {

}
