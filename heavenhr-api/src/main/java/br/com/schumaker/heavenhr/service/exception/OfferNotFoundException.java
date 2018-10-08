package br.com.schumaker.heavenhr.service.exception;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
public class OfferNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -6144298528346978735L;

	public OfferNotFoundException(String message) {
		super(message);
	}
	
	public OfferNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
