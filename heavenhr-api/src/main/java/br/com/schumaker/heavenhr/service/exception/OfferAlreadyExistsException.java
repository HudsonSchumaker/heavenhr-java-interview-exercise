package br.com.schumaker.heavenhr.service.exception;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
public class OfferAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = -1819764733171614041L;
	
	public OfferAlreadyExistsException(String message) {
		super(message);
	}
}
