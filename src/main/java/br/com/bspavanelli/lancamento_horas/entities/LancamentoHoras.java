package br.com.bspavanelli.lancamento_horas.entities;

import java.time.LocalTime;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

@Entity
public class LancamentoHoras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Campo data não pode ser nulo")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	private Calendar dataLancamento;

	private LocalTime horaEntrada;

	private LocalTime horaSaida;

	private LocalTime ajusteHora;

	@ManyToOne
	private Usuario usuario;

	public LancamentoHoras() {
	}

	public LancamentoHoras(Long id, @NotNull(message = "Campo data não pode ser nulo") Calendar dataLancamento,
			LocalTime horaEntrada, LocalTime horaSaida, LocalTime ajusteHora, Usuario usuario) {
		this.id = id;
		this.dataLancamento = dataLancamento;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
		this.ajusteHora = ajusteHora;
		this.usuario = usuario;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
