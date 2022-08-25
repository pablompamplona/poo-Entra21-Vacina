package exception;

public class VacinaPesquisadorInvalidoException extends Exception {
	
	public VacinaPesquisadorInvalidoException(String mensagem, Exception e) {
		super (mensagem,e);
	}
	
	public VacinaPesquisadorInvalidoException(String mensagem) {
		super (mensagem);
	}

}
