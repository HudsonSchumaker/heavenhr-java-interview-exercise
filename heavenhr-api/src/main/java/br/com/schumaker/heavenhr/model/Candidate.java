package br.com.schumaker.heavenhr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@Entity
public class Candidate implements Serializable{

	private static final long serialVersionUID = -6632584032405565475L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long canditadeId;
	
	@Column(unique = true, nullable = false)
	@NotEmpty(message = "Candidate's email can not be empty.")
	private String candidateEmail;
	
	public Candidate() {
	}

	public Long getCanditadeId() {
		return canditadeId;
	}

	public void setCanditadeId(Long canditadeId) {
		this.canditadeId = canditadeId;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}
}
