package controller;

import exception.VacinaDataInicioInvalidaException;
import exception.VacinaEstagioInvalidoException;
import exception.VacinaPaisInvalidoException;
import exception.VacinaPesquisadorInvalidoException;
import model.entidade.Vacina;
import model.repository.VacinaRepository;

public class VacinaController {
	
	VacinaRepository vr = new VacinaRepository();
	
	public Vacina inserir(Vacina novaVacina) throws VacinaPesquisadorInvalidoException, 
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
