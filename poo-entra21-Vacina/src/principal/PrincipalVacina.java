package principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import controller.PesquisadorController;
import controller.VacinaController;
import exception.PesquisadorCpfInvalidoException;
import exception.PesquisadorMatriculaInvalidaException;
import exception.PesquisadorNomeInvalidoException;
import exception.VacinaDataInicioInvalidaException;
import exception.VacinaEstagioInvalidoException;
import exception.VacinaPaisInvalidoException;
import exception.VacinaPesquisadorInvalidoException;
import model.entidade.Pesquisador;
import model.entidade.Vacina;
import model.repository.PesquisadorRepository;
import model.repository.VacinaRepository;

public class PrincipalVacina {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		PesquisadorController pc = new PesquisadorController();
		VacinaController vc = new VacinaController();
//		PesquisadorRepository pr = new PesquisadorRepository();
//		VacinaRepository vr = new VacinaRepository();
		
	
		
		Pesquisador pesq1 = new Pesquisador("Vampeta", null, "4321");
		try {
			pc.inserir(pesq1);
		} catch (PesquisadorNomeInvalidoException | PesquisadorCpfInvalidoException | PesquisadorMatriculaInvalidaException e) {
			System.out.println(e.getMessage());
		}

//		pr.inserir(pesq1);
		Vacina vac1 = new Vacina(null, 3, sdf.parse("15/03/2021"), pesq1, pesq1.getIdPesquisador());
		try {
			vc.inserir(vac1);
		} catch (VacinaPesquisadorInvalidoException | VacinaPaisInvalidoException | 
				 VacinaEstagioInvalidoException | VacinaDataInicioInvalidaException e) {
			System.out.println(e.getMessage());
		}

//		vr.inserir(vac1);
//		Pesquisador pesquisador = pr.pesquisarPorId(1);
//		System.out.println(pesquisador);	
//		
//		Vacina vac = vr.pesquisarPorId(3);
//		System.out.println(vac);
		
//		VacinaRepository vacinaRep = new VacinaRepository();
//		ArrayList<Vacina> vacinas = vacinaRep.pesquisarTodas();
//		
//		for(Vacina v: vacinas) {
//			System.out.println(v);
//		}
		
	}

}
