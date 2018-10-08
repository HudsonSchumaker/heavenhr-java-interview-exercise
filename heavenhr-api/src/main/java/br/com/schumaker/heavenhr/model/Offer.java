package br.com.schumaker.heavenhr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiParam;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@Entity
public class Offer implements Serializable{

	private static final long serialVersionUID = -2189894000046340203L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long offerId;
	
	@Column(unique = true)
	@NotEmpty(message = "Job Title can not be empty.")
	private String jobTitle;
	
	@Temporal(TemporalType.DATE)
	@ApiParam(value = "Date format YYYY-MM-DD") 
	private Date startDate;
	
	@Column(columnDefinition = "INTEGER default '0'")
	private Integer numberOfApplication;
	

	public Offer() {
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getNumberOfApplication() {
		return numberOfApplication;
	}

	public void setNumberOfApplication(Integer numberOfApplication) {
		this.numberOfApplication = numberOfApplication;
	}
}
