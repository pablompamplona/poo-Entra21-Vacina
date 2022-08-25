package exception;

public class PesquisadorCpfInvalidoException extends Exception {
	
	public PesquisadorCpfInvalidoException (String mensagem, Exception e) {
		super(mensagem, e);
	}
	
	public PesquisadorCpfInvalidoException (String mensagem) {
		super(mensagem);
	}

}
