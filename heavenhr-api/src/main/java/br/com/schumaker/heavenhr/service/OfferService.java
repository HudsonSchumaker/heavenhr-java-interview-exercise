package br.com.schumaker.heavenhr.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.schumaker.heavenhr.model.Offer;
import br.com.schumaker.heavenhr.repository.OfferRepository;
import br.com.schumaker.heavenhr.service.exception.OfferAlreadyExistsException;
import br.com.schumaker.heavenhr.service.exception.OfferNotFoundException;
import br.com.schumaker.heavenhr.util.HeavenHrCommons;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@Service
public class OfferService {

	@Autowired
	protected OfferRepository offerRepository;
	
	public Offer getById(Long id) {
		Offer offer = offerRepository.findOne(id);
		if(null == offer) {
			throw new OfferNotFoundException(HeavenHrCommons.OFFER_NOT_FOUND);
		}
		return offer;
	}
	
	public List<Offer> listAll(){
		return offerRepository.findAll();
	}
	
	public Page<Offer> pageableList(int page, int count) { 
		Pageable pages = new PageRequest(page, count);
        return offerRepository.findAll(pages);
    }
    
    public Offer saveOffer(Offer offer) {
    	offer.setNumberOfApplication(0); // h2 is not accepting the columnDefinition = "INTEGER default '0'""
    	try{
    		offer = offerRepository.save(offer);
    	}catch (ConstraintViolationException | DataIntegrityViolationException e) {
			throw new OfferAlreadyExistsException(HeavenHrCommons.OFFER_ALREADY_EXISTS);
		}
        return offer;
    }
}
