package br.com.bspavanelli.lancamento_horas.services;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bspavanelli.lancamento_horas.dto.lancamento_horas.LancamentoHorasNewDto;
import br.com.bspavanelli.lancamento_horas.entities.Feriado;
import br.com.bspavanelli.lancamento_horas.entities.LancamentoHoras;
import br.com.bspavanelli.lancamento_horas.entities.Usuario;
import br.com.bspavanelli.lancamento_horas.repositories.FeriadoRepository;
import br.com.bspavanelli.lancamento_horas.repositories.LancamentoHorasRepository;

@Service
public class LancamentoHorasService {

	@Autowired
	LancamentoHorasRepository repo;

	@Autowired
	FeriadoRepository feriadoRepository;

	@Autowired
	UsuarioService usuarioService;

	public boolean isFeriado(Calendar data) {
		List<Feriado> feriados = feriadoRepository.findAll();

		for (Feriado feriado : feriados) {
			if (feriado.isRecorrente() && data.get(Calendar.MONTH) == feriado.getData()
				.get(Calendar.MONTH) && data.get(Calendar.DAY_OF_MONTH) == feriado.getData()
					.get(Calendar.DAY_OF_MONTH)) {
				return true;
			}
			if (!feriado.isRecorrente() && data.equals(feriado.getData())) {
				return true;
			}
		}

		return false;
	}

	public LancamentoHoras insert(LancamentoHorasNewDto objDto) {
		Usuario usuario = usuarioService.findUsuario(objDto.getUsuario());

		return repo.save(fromDto(objDto, usuario));
	}

	public LancamentoHoras fromDto(LancamentoHorasNewDto objDto, Usuario usuario) {
		LancamentoHoras lancamento = new LancamentoHoras(null, objDto.getDataLancamento(), objDto.getHoraEntrada(),
				objDto.getHoraSaida(), setAjusteHora(objDto), usuario);

		return lancamento;
	}

	private LocalTime setAjusteHora(LancamentoHorasNewDto lancamentoHorasDto) {
		if (isFeriado(lancamentoHorasDto.getDataLancamento())) {
			int ajusteDeHoras = (int) (ChronoUnit.MINUTES.between(lancamentoHorasDto.getHoraEntrada(), lancamentoHorasDto.getHoraSaida())
					* 0.4);
			return LocalTime.of(0, ajusteDeHoras);
		}
		return LocalTime.of(0, 0, 0);
	}
}
