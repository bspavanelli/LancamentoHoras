package br.com.bspavanelli.lancamento_horas.dto.feriado;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.bspavanelli.lancamento_horas.utils.CalendarDeserializer;

public class FeriadoNewDto {

	@NotNull(message = "Campo data não pode ser nulo")
	@JsonDeserialize(using = CalendarDeserializer.class)
	private Calendar data;

	@NotNull(message = "Campo recorrente não pode ser nulo")
	private boolean recorrente;

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
