package br.com.comprefacil.exception;

/**
 * Excecao para tratamento de ceps invalidos.
 * @author Bruno Moura
 */
public class CepInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CepInvalidoException(String message){ super(message); }

}
