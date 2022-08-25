package exception;

public class VacinaDataInicioInvalidaException extends Exception {
	
	public VacinaDataInicioInvalidaException(String mensagem, Exception e) {
		super (mensagem,e);
	}
	
	public VacinaDataInicioInvalidaException(String mensagem) {
		super (mensagem);
	}

}
