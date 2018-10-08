package br.com.schumaker.heavenhr.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import br.com.schumaker.heavenhr.model.Application;
import br.com.schumaker.heavenhr.model.Candidate;
import br.com.schumaker.heavenhr.model.Offer;
import br.com.schumaker.heavenhr.repository.ApplicationRepository;
import br.com.schumaker.heavenhr.repository.CandidateRepository;
import br.com.schumaker.heavenhr.repository.OfferRepository;

/**
 *
 * @author Hudson Schumaker
 * @version 1.0.0
 */
public class ApplicationServiceTests {
	
	private Application application;
	private Application applicationWithID;
	private Candidate candidate;
	private Offer offerSr;
	
	@Spy
	private ApplicationService applicationService;
	
	@Before
	public void initTestCase() {
		offerSr = new Offer();
		offerSr.setOfferId(1L);
		offerSr.setJobTitle("Java Sr. Developer");
		offerSr.setStartDate(new Date());
		offerSr.setNumberOfApplication(0);
		
		candidate = new Candidate();
		candidate.setCanditadeId(1L);
		candidate.setCandidateEmail("hugo@me.com");
		
		application = new Application();
		application.setCandidate(candidate);
		application.setOffer(offerSr);
		application.setResumeText("I love java");
		
		applicationWithID = new Application();
		applicationWithID.setApplicationId(1L);
		applicationWithID.setCandidate(candidate);
		applicationWithID.setOffer(offerSr);
		applicationWithID.setResumeText("I love java");

		applicationService  = Mockito.spy(new ApplicationService());
	}
	
	@Test
	public void testSaveApplication() {
		ApplicationRepository applicationRepository = Mockito.mock(ApplicationRepository.class);
		Mockito.when(applicationRepository.save(application)).thenReturn(applicationWithID);
		
		OfferRepository       offerRepository       = Mockito.mock(OfferRepository.class);
		Mockito.when(offerRepository.exists(Mockito.anyLong())).thenReturn(true);
		
		CandidateRepository   candidateRepository   = Mockito.mock(CandidateRepository.class);
		Mockito.when(candidateRepository.exists(Mockito.anyLong())).thenReturn(true);
		
		applicationService.applicationRepository = applicationRepository;
		applicationService.offerRepository = offerRepository;
		applicationService.candidateRepository = candidateRepository;
		
		Mockito.doNothing().when(applicationService).udpdateOffer(Mockito.anyLong());
		application = applicationService.saveApplication(application);
		Long id = application.getApplicationId();
		Assert.assertEquals(id.intValue(), 1);
	}
}
