package br.com.bspavanelli.lancamento_horas.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bspavanelli.lancamento_horas.dto.lancamento_horas.LancamentoHorasNewDto;
import br.com.bspavanelli.lancamento_horas.entities.LancamentoHoras;
import br.com.bspavanelli.lancamento_horas.services.LancamentoHorasService;

@RestController
@RequestMapping(value = "/lancamento_horas")
public class LancamentoHorasController {

	@Autowired
	LancamentoHorasService service;

//	@GetMapping(value = "/list")
//	public Page<FeriadoDto> list(
//			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
//		Page<Feriado> feriado = service.findAll(pageable);
//		Page<FeriadoDto> dto = service.toDto(feriado);
//		return dto;
//	}
//
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<FeriadoDto> find(@PathVariable Long id) {
//		Feriado obj = service.find(id);
//		FeriadoDto dto = new FeriadoDto(obj);
//
//		return ResponseEntity.ok()
//			.body(dto);
//	}

	@PostMapping(value = "/insert")
	public ResponseEntity<Void> insert(@Valid @RequestBody LancamentoHorasNewDto objDto) {
		LancamentoHoras obj = service.insert(objDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(obj.getId())
			.toUri();

		return ResponseEntity.created(uri)
			.build();
	}

//	@PutMapping(value = "/update/{id}")
//	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody FeriadoDto objDto) {
//		Feriado obj = service.fromDto(objDto);
//		obj.setId(id);
//		obj = service.update(obj);
//
//		return ResponseEntity.noContent()
//			.build();
//	}

//	@DeleteMapping(value = "/delete/{id}")
//	public ResponseEntity<Void> removeUsuario(@PathVariable Long id) {
//		service.delete(id);
//
//		return ResponseEntity.noContent()
//			.build();
//	}
}
