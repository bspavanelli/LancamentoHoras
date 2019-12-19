package br.com.bspavanelli.lancamento_horas.dto.feriado;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.bspavanelli.lancamento_horas.entities.Feriado;
import br.com.bspavanelli.lancamento_horas.utils.CalendarDeserializer;

public class FeriadoDto {

	private Long id;

	@NotNull(message = "Campo data não pode ser nulo")
	@JsonDeserialize(using = CalendarDeserializer.class)
	private Calendar data;

	@NotNull(message = "Campo recorrente não pode ser nulo")
	private boolean recorrente;

	public FeriadoDto() {
	}

	public FeriadoDto(Feriado obj) {
		this.id = obj.getId();
		this.data = obj.getData();
		this.recorrente = obj.isRecorrente();
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
