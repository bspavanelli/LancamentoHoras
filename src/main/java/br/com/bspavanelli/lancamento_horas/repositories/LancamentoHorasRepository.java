package br.com.bspavanelli.lancamento_horas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bspavanelli.lancamento_horas.entities.LancamentoHoras;

public interface LancamentoHorasRepository extends JpaRepository<LancamentoHoras, Long>{

}
