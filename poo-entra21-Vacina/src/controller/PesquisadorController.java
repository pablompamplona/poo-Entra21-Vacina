package controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.PesquisadorCpfInvalidoException;
import exception.PesquisadorMatriculaInvalidaException;
import exception.PesquisadorNomeInvalidoException;
import model.entidade.Pesquisador;
import model.repository.PesquisadorRepository;

@RestController
@RequestMapping("/vacina/pesquisador")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class PesquisadorController {
	

	private PesquisadorRepository pr = new PesquisadorRepository();
	
	@PostMapping
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
	
	@GetMapping
	public ArrayList<Pesquisador> listarPesquisadores(){
		return pr.listarPesquisadores();
	}
	
	@GetMapping("/{id}")
	public Pesquisador pesquisarPorId(@PathVariable Integer id) {
		return pr.pesquisarPorId(id);
		
	}
	
	
	
	

}
