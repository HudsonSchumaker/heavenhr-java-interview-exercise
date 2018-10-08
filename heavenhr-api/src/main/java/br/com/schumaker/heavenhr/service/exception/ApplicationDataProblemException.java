package br.com.schumaker.heavenhr.service.exception;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
public class ApplicationDataProblemException extends RuntimeException {

	private static final long serialVersionUID = 3637553501084895408L;

	public ApplicationDataProblemException(String message) {
		super(message);
	}
}
