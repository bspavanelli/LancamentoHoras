package br.com.bspavanelli.lancamento_horas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bspavanelli.lancamento_horas.dto.usuario.UsuarioDto;
import br.com.bspavanelli.lancamento_horas.dto.usuario.UsuarioNewDto;
import br.com.bspavanelli.lancamento_horas.dto.usuario.UsuarioUpdateDto;
import br.com.bspavanelli.lancamento_horas.entities.Usuario;
import br.com.bspavanelli.lancamento_horas.repositories.UsuarioRepository;
import br.com.bspavanelli.lancamento_horas.services.exceptions.DataIntegrityException;
import br.com.bspavanelli.lancamento_horas.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repo;

	public Page<UsuarioDto> findUsuarios(String nomeUsuario, Pageable pageable) {
		if (nomeUsuario != null) {
			Page<Usuario> usuarios = repo.findByNomeContainsIgnoreCase(nomeUsuario, pageable);
			return toPageableDto(usuarios);
		} else {
			Page<Usuario> usuarios = repo.findAll(pageable);
			return toPageableDto(usuarios);
		}
	}

	public Usuario findUsuario(Long id) {
		Optional<Usuario> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	public Usuario insert(Usuario obj) {
		return repo.save(obj);
	}

	public Usuario update(Usuario obj) {
		Usuario newOBJ = findUsuario(obj.getId());
		updateData(newOBJ, obj);
		return repo.save(newOBJ);
	}

	public void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setLogin(obj.getLogin());
		newObj.setSenha(obj.getSenha());
	}
	
	public void delete(Long id) {
		findUsuario(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um usuário que possui lançamento de horas.");
		}		
	}

	public void checkOldPassword(Long id, String old) {
		Usuario usu = repo.findById(id).get();
		if (old.equals(usu.getSenha()))
			return;
		else
			throw new DataIntegrityException("A senha do usuário não confere com a senha enviada.");
	}

	public static Page<UsuarioDto> toPageableDto(Page<Usuario> usuarios) {
		return usuarios.map(UsuarioDto::new);
	}

	public Usuario fromDto(UsuarioNewDto newDto) {
		return new Usuario(null, newDto.getNome(), newDto.getLogin(), newDto.getSenha());
	}

	public Usuario fromDto(UsuarioUpdateDto dto) {
		return new Usuario(null, dto.getNome(), dto.getLogin(), dto.getSenha());
	}

}
