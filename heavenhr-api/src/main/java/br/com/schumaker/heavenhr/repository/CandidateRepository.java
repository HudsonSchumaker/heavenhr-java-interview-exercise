package br.com.schumaker.heavenhr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.schumaker.heavenhr.model.Candidate;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{}
