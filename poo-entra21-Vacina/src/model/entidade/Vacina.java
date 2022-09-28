package model.entidade;

import java.util.Date;

public class Vacina {

	private int id;
	private String paisOrigem;
	private int estagioPesquisa;
	private Date dataInicioPesquisa;
	private Pesquisador pesquisador;
	
	
	public Vacina() {
	}
	
	public Vacina(String paisOrigem, int estagioPesquisa, Date dataInicioPesquisa, Pesquisador pesquisador,
			int idPesquisador) {
		super();
		this.paisOrigem = paisOrigem;
		this.estagioPesquisa = estagioPesquisa;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.pesquisador = pesquisador;
		
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}
	public int getEstagioPesquisa() {
		return estagioPesquisa;
	}
	public void setEstagioPesquisa(int estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}
	public Date getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}
	public void setDataInicioPesquisa(Date dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}
	
	public Pesquisador getPesquisador() {
		return pesquisador;
	}
	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}
	
	
	@Override
	public String toString() {
		return "Id: " + id + "\n" +
				"Pais de Origem: " + paisOrigem + "\n" +
				"Estagio de Pesquisa: " + estagioPesquisa + "\n" +
				"Inicio da Pesquisa: " + dataInicioPesquisa + "\n" + 
				"Pesquisador REsponsavel: " + pesquisador.getNome();
	}


}
