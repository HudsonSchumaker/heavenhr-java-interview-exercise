package br.com.schumaker.heavenhr.service.exception;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
public class CandidateAlreadyExistsExcepiton extends RuntimeException {

	private static final long serialVersionUID = 7922265294627146572L;

	public CandidateAlreadyExistsExcepiton(String message) {
		super(message);
	}
}
