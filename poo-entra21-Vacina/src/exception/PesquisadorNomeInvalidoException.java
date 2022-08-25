package exception;

public class PesquisadorNomeInvalidoException extends Exception {
	
	public PesquisadorNomeInvalidoException (String mensagem, Exception e) {
		super(mensagem, e);
	}
	
	public PesquisadorNomeInvalidoException (String mensagem) {
		super(mensagem);
	}

}
