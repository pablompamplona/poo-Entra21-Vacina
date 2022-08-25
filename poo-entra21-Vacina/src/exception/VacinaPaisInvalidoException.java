package exception;

public class VacinaPaisInvalidoException extends Exception {
	
	public VacinaPaisInvalidoException(String mensagem, Exception e) {
		super (mensagem,e);
	}
	
	public VacinaPaisInvalidoException(String mensagem) {
		super (mensagem);
	}

}
