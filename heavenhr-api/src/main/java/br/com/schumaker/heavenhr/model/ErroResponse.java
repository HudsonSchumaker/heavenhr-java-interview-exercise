package br.com.schumaker.heavenhr.model;

import org.springframework.http.HttpStatus;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
public class ErroResponse {

	private String title;
	private HttpStatus status;
	private Long timestamp;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
