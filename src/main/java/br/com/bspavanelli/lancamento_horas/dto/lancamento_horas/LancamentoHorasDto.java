package br.com.bspavanelli.lancamento_horas.dto.lancamento_horas;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import br.com.bspavanelli.lancamento_horas.dto.usuario.UsuarioDto;
import br.com.bspavanelli.lancamento_horas.entities.Usuario;

public class LancamentoHorasDto {

	private Long id;

	@NotNull(message = "Campo data não pode ser nulo")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	private Calendar dataLancamento;

	private LocalTime horaEntrada;

	private LocalTime horaSaida;

	private LocalTime ajusteHora = LocalTime.of(0, 0);

	private UsuarioDto usuario;

	public LancamentoHorasDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public LocalTime getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(LocalTime horaSaida) {
		this.horaSaida = horaSaida;
	}

	public LocalTime getAjusteHora() {
		return ajusteHora;
	}

	public void setAjusteHora(LocalTime ajusteHora) {
		this.ajusteHora = ajusteHora;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = new UsuarioDto(usuario);
	}

	public LocalTime getTotalHoras() {
		long duracaoPeriodo = ChronoUnit.MINUTES.between(horaEntrada, horaSaida);

		return LocalTime.of(ajusteHora.getHour(), ajusteHora.getMinute())
			.plusMinutes(duracaoPeriodo);
	}
}
