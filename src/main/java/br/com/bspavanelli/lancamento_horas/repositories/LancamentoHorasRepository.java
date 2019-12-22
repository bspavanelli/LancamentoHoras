package br.com.bspavanelli.lancamento_horas.repositories;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.bspavanelli.lancamento_horas.entities.LancamentoHoras;
import br.com.bspavanelli.lancamento_horas.entities.Usuario;

public interface LancamentoHorasRepository extends JpaRepository<LancamentoHoras, Long> {

	@Query("SELECT l FROM LancamentoHoras l WHERE l.usuario = ?1 AND l.dataLancamento = ?2 AND (l.horaEntrada BETWEEN ?3 AND ?4 OR l.horaSaida BETWEEN ?3 AND ?4)")
	List<LancamentoHoras> findLancamentoWithSameDate(Usuario usuario,
			Calendar dataLancamento, LocalTime entradaInicio, LocalTime entradaFim);
	
	@Query("SELECT l FROM LancamentoHoras l WHERE l.id != ?1 AND l.usuario = ?2 AND l.dataLancamento = ?3 AND (l.horaEntrada BETWEEN ?4 AND ?5 OR l.horaSaida BETWEEN ?4 AND ?5)")
	List<LancamentoHoras> findLancamentoWithSameDate(Long id, Usuario usuario,
			Calendar dataLancamento, LocalTime entradaInicio, LocalTime entradaFim);

}
