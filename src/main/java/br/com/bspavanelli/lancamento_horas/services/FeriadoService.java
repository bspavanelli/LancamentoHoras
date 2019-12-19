package br.com.bspavanelli.lancamento_horas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bspavanelli.lancamento_horas.dto.feriado.FeriadoDto;
import br.com.bspavanelli.lancamento_horas.dto.feriado.FeriadoNewDto;
import br.com.bspavanelli.lancamento_horas.entities.Feriado;
import br.com.bspavanelli.lancamento_horas.repositories.FeriadoRepository;
import br.com.bspavanelli.lancamento_horas.services.exceptions.ObjectNotFoundException;

@Service
public class FeriadoService {

	@Autowired
	FeriadoRepository repo;

	public Page<Feriado> findAll(Pageable pageable) {
		Page<Feriado> feriados = repo.findAll(pageable);
		return feriados;
	}

	public Feriado find(Long id) {
		Optional<Feriado> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Feriado não encontrado! Id: " + id + ", Tipo: " + Feriado.class.getName()));
	}

	public Feriado insert(Feriado obj) {
		return repo.save(obj);
	}

	public Feriado update(Feriado obj) {
		Feriado newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Feriado newObj, Feriado obj) {
		newObj.setData(obj.getData());
		newObj.setRecorrente(obj.isRecorrente());
	}

	public void delete(Long id) {
		find(id);
		repo.deleteById(id);
	}

	public Feriado fromDto(FeriadoNewDto objDto) {
		return new Feriado(null, objDto.getData(), objDto.isRecorrente());
	}

	public Feriado fromDto(FeriadoDto objDto) {
		return new Feriado(null, objDto.getData(), objDto.isRecorrente());
	}

	public FeriadoDto toDto(Feriado obj) {
		return new FeriadoDto(obj);
	}

	public Page<FeriadoDto> toDto(Page<Feriado> obj) {
		return obj.map(FeriadoDto::new);
	}

}
