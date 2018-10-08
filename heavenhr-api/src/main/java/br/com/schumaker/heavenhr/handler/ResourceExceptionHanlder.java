package br.com.schumaker.heavenhr.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.schumaker.heavenhr.model.ErroResponse;
import br.com.schumaker.heavenhr.service.exception.ApplicationDataProblemException;
import br.com.schumaker.heavenhr.service.exception.ApplicationNotFoundException;
import br.com.schumaker.heavenhr.service.exception.CandidateAlreadyExistsExcepiton;
import br.com.schumaker.heavenhr.service.exception.OfferAlreadyExistsException;
import br.com.schumaker.heavenhr.service.exception.OfferNotFoundException;
import br.com.schumaker.heavenhr.service.exception.WrongStatusProgressException;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@ControllerAdvice
public class ResourceExceptionHanlder {

	@ExceptionHandler(ApplicationNotFoundException.class)
	public ResponseEntity<ErroResponse> handleApplicationNotFoundException(ApplicationNotFoundException e, HttpServletRequest r){
		ErroResponse er = new ErroResponse();
		er.setTitle(e.getMessage());
		er.setStatus(HttpStatus.NOT_FOUND);
		er.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
	}
	
	@ExceptionHandler(OfferNotFoundException.class)
	public ResponseEntity<ErroResponse> handleOfferNotFoundException(OfferNotFoundException e, HttpServletRequest r){
		ErroResponse er = new ErroResponse();
		er.setTitle(e.getMessage());
		er.setStatus(HttpStatus.NOT_FOUND);
		er.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
	}
	
	@ExceptionHandler(OfferAlreadyExistsException.class)
	public ResponseEntity<ErroResponse> handleOfferAlreadyExistsException(OfferAlreadyExistsException e, HttpServletRequest r){
		ErroResponse er = new ErroResponse();
		er.setTitle(e.getMessage());
		er.setStatus(HttpStatus.CONFLICT);
		er.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(er);
	}
	
	@ExceptionHandler(CandidateAlreadyExistsExcepiton.class)
	public ResponseEntity<ErroResponse> handleCandidateAlreadyExistsExcepiton(CandidateAlreadyExistsExcepiton e, HttpServletRequest r){
		ErroResponse er = new ErroResponse();
		er.setTitle(e.getMessage());
		er.setStatus(HttpStatus.CONFLICT);
		er.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(er);
	}
	
	@ExceptionHandler(ApplicationDataProblemException.class)
	public ResponseEntity<ErroResponse> handleApplicationDataProblemException(ApplicationDataProblemException e, HttpServletRequest r){
		ErroResponse er = new ErroResponse();
		er.setTitle(e.getMessage());
		er.setStatus(HttpStatus.BAD_REQUEST);
		er.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	}
	
	@ExceptionHandler(WrongStatusProgressException.class)
	public ResponseEntity<ErroResponse> handleWrongStatusProgressException(WrongStatusProgressException e, HttpServletRequest r){
		ErroResponse er = new ErroResponse();
		er.setTitle(e.getMessage());
		er.setStatus(HttpStatus.BAD_REQUEST);
		er.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	}
}
