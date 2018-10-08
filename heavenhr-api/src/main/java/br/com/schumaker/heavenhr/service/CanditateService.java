package br.com.schumaker.heavenhr.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.schumaker.heavenhr.model.Candidate;
import br.com.schumaker.heavenhr.repository.CandidateRepository;
import br.com.schumaker.heavenhr.service.exception.CandidateAlreadyExistsExcepiton;
import br.com.schumaker.heavenhr.util.HeavenHrCommons;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@Service
public class CanditateService {

	@Autowired
	private CandidateRepository candidateRepository;
	
	public Candidate getById(Long id) {
		return candidateRepository.findOne(id);
	}
	
	public Candidate saveCandidate(Candidate candidate) {
		try {
			candidate = candidateRepository.save(candidate);
		}
		catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new CandidateAlreadyExistsExcepiton(HeavenHrCommons.CANDIDATE_ALREADY_EXISTS);
		}
		return candidate;
	}
}
