package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.VacinaDataInicioInvalidaException;
import exception.VacinaEstagioInvalidoException;
import exception.VacinaPaisInvalidoException;
import exception.VacinaPesquisadorInvalidoException;
import model.entidade.Vacina;
import model.repository.VacinaRepository;

@RestController
@RequestMapping("/vacina")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class VacinaController {
	
	
	private VacinaRepository vr = new VacinaRepository();
	
	
	@GetMapping
	public ArrayList<Vacina> pesquisarTodas(){
		return vr.pesquisarTodas();
	}
	
	@GetMapping("/{id}")
	public Vacina pesquisarPorId(@PathVariable Integer id){
		return vr.pesquisarPorId(id);
	}
	
	@GetMapping("/responsavel/{id_responsavel}")
	public List<Vacina> pesquisarPorIdResponsavel(@PathVariable(name = "id_responsavel") Integer id){
		return vr.pesquisarPorIdResponsavel(id);
	}
	
	@PostMapping
	public Vacina inserir(@RequestBody Vacina novaVacina) throws VacinaPesquisadorInvalidoException, 
		VacinaPaisInvalidoException, VacinaEstagioInvalidoException, VacinaDataInicioInvalidaException {
		
		if(novaVacina.getPesquisador() == null) {
			throw new VacinaPesquisadorInvalidoException("Informe Pesquisador Responsavel");
		}
		if (novaVacina.getPaisOrigem() == null || novaVacina.getPaisOrigem().trim().isEmpty()) {
			throw new VacinaPaisInvalidoException("Informe Pais de Origem");
		}
		if (novaVacina.getDataInicioPesquisa() == null) {
			throw new VacinaDataInicioInvalidaException("Informe Data Inicio Pesquisa");
		}
		if (novaVacina.getEstagioPesquisa() == 0) {
			throw new VacinaEstagioInvalidoException("Informe Estagio da Pesquisa");
		}
				
		novaVacina = vr.inserir(novaVacina);
		
		return novaVacina;
	}

}
