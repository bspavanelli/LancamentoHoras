package br.com.bspavanelli.lancamento_horas.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String login;
	private String senha;

	@OneToMany(mappedBy = "usuario")
	private List<LancamentoHoras> lancamentoHoras;

	public Usuario() {
	}

	public Usuario(Long id, String nome, String login, String senha) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<LancamentoHoras> getLancamentoHoras() {
		return lancamentoHoras;
	}

	public void setLancamentoHoras(List<LancamentoHoras> lancamentoHoras) {
		this.lancamentoHoras = lancamentoHoras;
	}

}
