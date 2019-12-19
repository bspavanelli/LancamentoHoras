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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bspavanelli.lancamento_horas.dto.usuario.UsuarioDto;
import br.com.bspavanelli.lancamento_horas.dto.usuario.UsuarioNewDto;
import br.com.bspavanelli.lancamento_horas.dto.usuario.UsuarioUpdateDto;
import br.com.bspavanelli.lancamento_horas.entities.Usuario;
import br.com.bspavanelli.lancamento_horas.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping(value = "/list")
	public Page<UsuarioDto> findUsuarios(@RequestParam(required = false) String nomeUsuario,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
		Page<UsuarioDto> usuario = service.findUsuarios(nomeUsuario, pageable);

		return usuario;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDto> findUsuario(@PathVariable(value = "id") Long id) {
		UsuarioDto dto = new UsuarioDto(service.findUsuario(id));

		return ResponseEntity.ok()
			.body(dto);
	}

	@PostMapping(value = "/insert")
	public ResponseEntity<Void> registerUsuario(@Valid @RequestBody UsuarioNewDto objDto) {
		Usuario obj = service.fromDto(objDto);
		obj = service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(obj.getId())
			.toUri();

		return ResponseEntity.created(uri)
			.build();
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Void> editUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateDto objDto) {
		service.checkOldPassword(id, objDto.getSenhaOld());

		Usuario obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent()
			.build();
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> removeUsuario(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
}
