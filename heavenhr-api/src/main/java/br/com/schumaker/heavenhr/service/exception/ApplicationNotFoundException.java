package br.com.schumaker.heavenhr.service.exception;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
public class ApplicationNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 6825537414226673470L;

	public ApplicationNotFoundException(String message) {
		super(message);
	}
	
	public ApplicationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
