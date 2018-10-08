package br.com.schumaker.heavenhr.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.schumaker.heavenhr.model.Application;
import br.com.schumaker.heavenhr.model.EnumApplicationStatus;
import br.com.schumaker.heavenhr.model.Offer;
import br.com.schumaker.heavenhr.repository.ApplicationRepository;
import br.com.schumaker.heavenhr.repository.CandidateRepository;
import br.com.schumaker.heavenhr.repository.OfferRepository;
import br.com.schumaker.heavenhr.service.exception.ApplicationDataProblemException;
import br.com.schumaker.heavenhr.service.exception.ApplicationNotFoundException;
import br.com.schumaker.heavenhr.service.exception.WrongStatusProgressException;
import br.com.schumaker.heavenhr.util.HeavenHrCommons;
import br.com.schumaker.heavenhr.util.TriggerNotification;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@Service
public class ApplicationService {

	@Autowired
	protected ApplicationRepository applicationRepository;
	
	@Autowired
	protected OfferRepository offerRepository;
	
	@Autowired
	protected CandidateRepository candidateRepository;

	public Application getById(Long id) {
		Application application = applicationRepository.findOne(id);
		if(null == application) {
			throw new ApplicationNotFoundException(HeavenHrCommons.APPLICATION_NOT_FOUND);
		}
		return application;
	}

	public List<Application> listAll() {
		return  applicationRepository.findAll();
	}
	
	public Application appPerOffer(Long idOffer, Long idApp){
		Application application = applicationRepository.findApplicationByOfferId(idOffer, idApp);
		if(null == application) {
			throw new ApplicationNotFoundException(HeavenHrCommons.APPLICATION_NOT_FOUND);
		}
		return application;
	}
	
	public List<Application> listPerOffer(Long id){
		return applicationRepository.findApplicationListByOfferId(id);
	}
	
	public Page<Application> pageableList(int page, int count) { 
        Pageable pages = new PageRequest(page, count);
        return applicationRepository.findAll(pages);
    }
	
	@Transactional(readOnly = false)
	public Application saveApplication(Application application) {
		try{
			if(offerRepository.exists(application.getOffer().getOfferId())) {
				if(candidateRepository.exists(application.getCandidate().getCanditadeId())) {
					application = applicationRepository.save(application);
					udpdateOffer(application.getOffer().getOfferId());
				} else {throw new ApplicationDataProblemException(HeavenHrCommons.CANDIDATE_NOT_EXISTS);}
			} else {throw new ApplicationDataProblemException(HeavenHrCommons.OFFER_NOT_EXISTS);}
		} catch (ConstraintViolationException | DataIntegrityViolationException e) {
			throw new ApplicationDataProblemException(HeavenHrCommons.CANDIDATE_ALREADY_APPLIED);
		}
		return application;
	}
	
	@Transactional(readOnly = false)
	public Application progressStatus(String status, Long id) {
		if(null == EnumApplicationStatus.getApplicationStatusByName(status)) {
			throw new WrongStatusProgressException(HeavenHrCommons.WRONG_STATUS);
		}
		Application application = applicationRepository.findOne(id);
		application.setStatus(EnumApplicationStatus.getApplicationStatusByName(status));
		application = applicationRepository.saveAndFlush(application);
		TriggerNotification.trigger(application);
		return application;
	}
	
	public Long getNumberOfApplications() {
		return applicationRepository.count();
	}
	
	protected void udpdateOffer(Long id) {
		Offer offer = offerRepository.findOne(id);
		offer.setNumberOfApplication(offer.getNumberOfApplication() + 1);
		offerRepository.save(offer);
	}
}
