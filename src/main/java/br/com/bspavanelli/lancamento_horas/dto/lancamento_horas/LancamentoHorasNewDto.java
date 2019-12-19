package br.com.bspavanelli.lancamento_horas.dto.lancamento_horas;

import java.time.LocalTime;
import java.util.Calendar;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.bspavanelli.lancamento_horas.utils.CalendarDeserializer;

public class LancamentoHorasNewDto {

	@NotNull(message = "Campo data não pode ser nulo")
	@JsonDeserialize(using = CalendarDeserializer.class)
	private Calendar dataLancamento;

	@NotNull(message = "Campo data não pode ser nulo")
	private LocalTime horaEntrada;

	@NotNull(message = "Campo data não pode ser nulo")
	private LocalTime horaSaida;

	@NotNull(message = "Campo data não pode ser nulo")
	private Long usuario;

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

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
}
