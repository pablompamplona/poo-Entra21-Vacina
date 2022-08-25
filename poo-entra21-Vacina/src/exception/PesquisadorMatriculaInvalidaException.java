package exception;

public class PesquisadorMatriculaInvalidaException extends Exception {
	
	public PesquisadorMatriculaInvalidaException (String mensagem, Exception e) {
		super(mensagem, e);
	}
	
	public PesquisadorMatriculaInvalidaException (String mensagem) {
		super(mensagem);
	}

}
