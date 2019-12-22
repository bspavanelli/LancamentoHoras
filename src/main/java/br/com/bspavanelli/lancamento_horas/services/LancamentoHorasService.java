package br.com.bspavanelli.lancamento_horas.services;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bspavanelli.lancamento_horas.dto.lancamento_horas.LancamentoHorasDto;
import br.com.bspavanelli.lancamento_horas.dto.lancamento_horas.LancamentoHorasNewDto;
import br.com.bspavanelli.lancamento_horas.dto.lancamento_horas.LancamentoHorasUpdateDto;
import br.com.bspavanelli.lancamento_horas.entities.Feriado;
import br.com.bspavanelli.lancamento_horas.entities.LancamentoHoras;
import br.com.bspavanelli.lancamento_horas.entities.Usuario;
import br.com.bspavanelli.lancamento_horas.repositories.FeriadoRepository;
import br.com.bspavanelli.lancamento_horas.repositories.LancamentoHorasRepository;
import br.com.bspavanelli.lancamento_horas.services.exceptions.LancamentoExistsException;
import br.com.bspavanelli.lancamento_horas.services.exceptions.ObjectNotFoundException;
import br.com.bspavanelli.lancamento_horas.services.exceptions.StartTimeEqualsToEndTimeException;
import br.com.bspavanelli.lancamento_horas.services.exceptions.StartTimeGreaterThanEndTimeException;

@Service
public class LancamentoHorasService {

	@Autowired
	LancamentoHorasRepository repo;

	@Autowired
	FeriadoRepository feriadoRepository;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	ModelMapper modelMapper;

	public Page<LancamentoHoras> findAll(Pageable pageable) {
		Page<LancamentoHoras> lancamentoHoras = repo.findAll(pageable);
		return lancamentoHoras;
	}

	public LancamentoHoras findById(Long id) {
		Optional<LancamentoHoras> lancamentoHoras = repo.findById(id);

		return lancamentoHoras.orElseThrow(() -> new ObjectNotFoundException(
				"Lançamento de horas não encontrado! Id: " + id + ", Tipo: " + LancamentoHoras.class.getName()));
	}

	public LancamentoHorasDto insert(@Valid LancamentoHorasNewDto lancamentoHorasNewDto) {
		checkStartAndEndTime(lancamentoHorasNewDto.getHoraEntrada(), lancamentoHorasNewDto.getHoraSaida());

		Usuario usuario = usuarioService.findUsuario(lancamentoHorasNewDto.getUsuario());
		LancamentoHoras lancamentoHoras = modelMapper.map(lancamentoHorasNewDto, LancamentoHoras.class);
		lancamentoHoras.setUsuario(usuario);
		lancamentoHoras.setAjusteHora(setAjusteHora(lancamentoHoras));

		if (lancamentoExists(lancamentoHoras, null)) {
			throw new LancamentoExistsException("Já existe um lançamento nesse período para esse usuário.");
		}

		LancamentoHorasDto lancamentoHorasDto = modelMapper.map(repo.save(lancamentoHoras), LancamentoHorasDto.class);

		return lancamentoHorasDto;
	}

	public LancamentoHoras update(LancamentoHorasUpdateDto newLancamentoUpdateHoras, Long id) {
		checkStartAndEndTime(newLancamentoUpdateHoras.getHoraEntrada(), newLancamentoUpdateHoras.getHoraSaida());

		LancamentoHoras savedLancamentoHoras = findById(id);

		LancamentoHoras newLancamentoHoras = modelMapper.map(newLancamentoUpdateHoras, LancamentoHoras.class);
		newLancamentoHoras.setId(id);
		newLancamentoHoras.setUsuario(savedLancamentoHoras.getUsuario());

		if (lancamentoExists(newLancamentoHoras, id)) {
			throw new LancamentoExistsException("Já existe um lançamento nesse período para esse usuário.");
		}

		modelMapper.map(newLancamentoHoras, savedLancamentoHoras);

		savedLancamentoHoras.setAjusteHora(setAjusteHora(savedLancamentoHoras));

		return repo.save(savedLancamentoHoras);
	}

	public void delete(Long id) {
		findById(id);
		repo.deleteById(id);
	}

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

	private LocalTime setAjusteHora(LancamentoHoras lancamento) {
		if (isFeriado(lancamento.getDataLancamento())) {
			int ajusteDeHoras = (int) (ChronoUnit.MINUTES.between(lancamento.getHoraEntrada(),
					lancamento.getHoraSaida()) * 0.4);
			return LocalTime.of(0, 0)
				.plusMinutes(ajusteDeHoras);
		}
		return LocalTime.of(0, 0);
	}

	public boolean lancamentoExists(LancamentoHoras lancamentoHoras, Long id) {
		List<LancamentoHoras> lancamentos = new ArrayList<>();
		if (id == null) {
			lancamentos = repo.findLancamentoWithSameDate(lancamentoHoras.getUsuario(),
					lancamentoHoras.getDataLancamento(), lancamentoHoras.getHoraEntrada(),
					lancamentoHoras.getHoraSaida());
		} else {
			lancamentos = repo.findLancamentoWithSameDate(id, lancamentoHoras.getUsuario(),
					lancamentoHoras.getDataLancamento(), lancamentoHoras.getHoraEntrada(),
					lancamentoHoras.getHoraSaida());
		}
		return lancamentos.size() > 0 ? true : false;
	}

	public void checkStartAndEndTime(LocalTime start, LocalTime end) {
		if (start.equals(end)) 
			throw new StartTimeEqualsToEndTimeException("Hora de entrada igual a hora de saída.");
		
		if (!start.isBefore(end))
			throw new StartTimeGreaterThanEndTimeException("Hora de entrada maior que hora de saída.");

	}
}
