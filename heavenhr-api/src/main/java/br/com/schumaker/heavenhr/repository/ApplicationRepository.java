package br.com.schumaker.heavenhr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.schumaker.heavenhr.model.Application;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{
	
	@Query("SELECT a FROM Application a WHERE OFFER_ID = ?1")
	List<Application> findApplicationListByOfferId(Long id);
	
	@Query("SELECT a FROM Application a WHERE OFFER_ID = ?1 AND APPLICATION_ID = ?2")
	Application findApplicationByOfferId(Long idOffer, Long idApp);
}