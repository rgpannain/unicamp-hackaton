package br.com.comprefacil.exception;

public class CorreiosException extends Exception {
	
	private static final long serialVersionUID = 1771526374664861685L;
	private final int codigo;
	
	public CorreiosException(String message, int codigo){
		super(message);
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return this.codigo;
	}

}
