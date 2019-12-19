package br.com.bspavanelli.lancamento_horas.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bspavanelli.lancamento_horas.dto.feriado.FeriadoDto;
import br.com.bspavanelli.lancamento_horas.dto.feriado.FeriadoNewDto;
import br.com.bspavanelli.lancamento_horas.entities.Feriado;
import br.com.bspavanelli.lancamento_horas.services.FeriadoService;

@RestController
@RequestMapping(value = "/feriado")
public class FeriadoController {

	@Autowired
	FeriadoService service;

	@GetMapping()
	public Page<FeriadoDto> list(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
		Page<Feriado> feriado = service.findAll(pageable);
		Page<FeriadoDto> dto = service.toDto(feriado);
		return dto;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<FeriadoDto> find(@PathVariable Long id) {
		Feriado obj = service.find(id);
		FeriadoDto dto = new FeriadoDto(obj);

		return ResponseEntity.ok()
			.body(dto);
	}

	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody FeriadoNewDto objDto) {
		Feriado obj = service.fromDto(objDto);
		obj = service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(obj.getId())
			.toUri();

		return ResponseEntity.created(uri)
			.build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody FeriadoDto objDto) {
		Feriado obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent()
			.build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> removeUsuario(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent()
			.build();
	}
}
