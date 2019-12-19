package br.com.bspavanelli.lancamento_horas.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UsuarioUpdateDto {
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "O login precisa ser um e-mail")
	private String login;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String senhaOld;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 6, message = "A senha precisa ter no mínimo 6 caracteres")
	private String senha;

	public UsuarioUpdateDto() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenhaOld() {
		return senhaOld;
	}

	public void setSenhaOld(String senhaOld) {
		this.senhaOld = senhaOld;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
