package br.com.bspavanelli.lancamento_horas.dto.usuario;

import br.com.bspavanelli.lancamento_horas.entities.Usuario;

public class UsuarioDto {
	private Long id;

	private String nome;
	private String login;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.login = usuario.getLogin();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getLogin() {
		return login;
	}

}
