package exception;

public class VacinaEstagioInvalidoException extends Exception {
	
	public VacinaEstagioInvalidoException(String mensagem, Exception e) {
		super (mensagem,e);
	}
	
	public VacinaEstagioInvalidoException(String mensagem) {
		super (mensagem);
	}

}
