package model.entidade;

public class Pesquisador {
	
	private Integer idPesquisador;
	private String nome;
	private String cpf;
	private String matricula;
	
	public Pesquisador() {
	}

	public Pesquisador(String nome, String cpf, String matricula) {
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
	}

	public Integer getIdPesquisador() {
		return idPesquisador;
	}

	public void setIdPesquisador(Integer idPesquisador) {
		this.idPesquisador = idPesquisador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "ID = " + idPesquisador + "\n" +
				"Nome = " + nome + "\n" +
 				"CPF = " + cpf + "\n" +
				"Matricula = " + matricula;
	}
	
	
	
	
}
