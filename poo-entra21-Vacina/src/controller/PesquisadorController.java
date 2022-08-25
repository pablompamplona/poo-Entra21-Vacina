package controller;

import exception.PesquisadorCpfInvalidoException;
import exception.PesquisadorMatriculaInvalidaException;
import exception.PesquisadorNomeInvalidoException;
import model.entidade.Pesquisador;
import model.repository.PesquisadorRepository;

public class PesquisadorController {
	
	PesquisadorRepository pr = new PesquisadorRepository();
	
	public Pesquisador inserir(Pesquisador novoPesquisador) throws PesquisadorMatriculaInvalidaException, 
										PesquisadorNomeInvalidoException, PesquisadorCpfInvalidoException {
		
		if (novoPesquisador.getNome() == null || novoPesquisador.getNome().trim().isEmpty()) {
			throw new PesquisadorNomeInvalidoException("Informe o Nome do Pesquisador");
		}
		else if (novoPesquisador.getCpf() == null || novoPesquisador.getCpf().trim().isEmpty()) {
			throw new PesquisadorCpfInvalidoException("Informe o CPF do Pesquisador");
		}
		else if (novoPesquisador.getMatricula() == null || novoPesquisador.getMatricula().trim().isEmpty()) {
			throw new PesquisadorMatriculaInvalidaException("Informe a Matricula do Pesquisador");
		}
		
		novoPesquisador = pr.inserir(novoPesquisador);
		
		return novoPesquisador;
	}

}
