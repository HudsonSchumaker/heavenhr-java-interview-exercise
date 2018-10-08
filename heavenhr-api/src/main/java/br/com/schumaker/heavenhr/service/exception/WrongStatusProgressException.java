package br.com.schumaker.heavenhr.service.exception;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
public class WrongStatusProgressException extends RuntimeException{

	private static final long serialVersionUID = -6678153957410869976L;

	public WrongStatusProgressException(String message) {
		super(message);
	}
	
	public WrongStatusProgressException(String message, Throwable cause) {
		super(message, cause);
	}
}