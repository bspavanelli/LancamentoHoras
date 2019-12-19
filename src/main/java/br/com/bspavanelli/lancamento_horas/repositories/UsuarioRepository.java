package br.com.bspavanelli.lancamento_horas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bspavanelli.lancamento_horas.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Page<Usuario> findByNomeContainsIgnoreCase(String nomeUsuario, Pageable page);

}
