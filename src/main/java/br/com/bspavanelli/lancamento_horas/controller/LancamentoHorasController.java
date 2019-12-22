package br.com.bspavanelli.lancamento_horas.controller;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import br.com.bspavanelli.lancamento_horas.dto.lancamento_horas.LancamentoHorasDto;
import br.com.bspavanelli.lancamento_horas.dto.lancamento_horas.LancamentoHorasNewDto;
import br.com.bspavanelli.lancamento_horas.dto.lancamento_horas.LancamentoHorasUpdateDto;
import br.com.bspavanelli.lancamento_horas.entities.LancamentoHoras;
import br.com.bspavanelli.lancamento_horas.services.LancamentoHorasService;

@RestController
@RequestMapping(value = "/lancamento_horas")
public class LancamentoHorasController {

	@Autowired
	LancamentoHorasService service;

	@Autowired
	ModelMapper modelMapper;

	@GetMapping()
	public ResponseEntity<Page<LancamentoHorasDto>> getAllPageable(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {

		Page<LancamentoHoras> lancamentos = service.findAll(pageable);

		return ResponseEntity.ok(lancamentos.map(lancamento -> modelMapper.map(lancamento, LancamentoHorasDto.class)));

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<LancamentoHorasDto> getById(@PathVariable Long id) {
		LancamentoHoras lancamentoHoras = service.findById(id);
		LancamentoHorasDto lancamentoHorasDto = modelMapper.map(lancamentoHoras, LancamentoHorasDto.class);

		return ResponseEntity.ok()
			.body(lancamentoHorasDto);
	}

	@PostMapping()
	public ResponseEntity<LancamentoHorasDto> insert(@Valid @RequestBody LancamentoHorasNewDto lancamentoHorasNewDto) {
		LancamentoHorasDto lancamentoHorasDto = service.insert(lancamentoHorasNewDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(lancamentoHorasDto.getId())
			.toUri();

		return ResponseEntity.created(uri)
			.body(lancamentoHorasDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<LancamentoHorasDto> update(@PathVariable Long id,
			@Valid @RequestBody LancamentoHorasUpdateDto lancamentoHorasUpdateDto) {
		LancamentoHoras lancamento = service.update(lancamentoHorasUpdateDto, id);

		return ResponseEntity.ok()
			.body(modelMapper.map(lancamento, LancamentoHorasDto.class));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent()
			.build();
	}
}
