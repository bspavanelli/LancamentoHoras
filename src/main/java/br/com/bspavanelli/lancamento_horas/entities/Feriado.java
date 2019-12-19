package br.com.bspavanelli.lancamento_horas.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.bspavanelli.lancamento_horas.utils.CalendarDeserializer;

@Entity
public class Feriado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Campo data não pode ser nulo")
	@JsonDeserialize(using = CalendarDeserializer.class)
	private Calendar data;

	private boolean recorrente;

	public Feriado() {
	}

	public Feriado(Long id, Calendar data, boolean recorrente) {
		this.id = id;
		this.data = data;
		this.recorrente = recorrente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public boolean isRecorrente() {
		return recorrente;
	}

	public void setRecorrente(boolean recorrente) {
		this.recorrente = recorrente;
	}

}
