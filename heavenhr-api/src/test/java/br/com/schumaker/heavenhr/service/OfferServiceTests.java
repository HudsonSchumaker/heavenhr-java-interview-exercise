package br.com.schumaker.heavenhr.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.schumaker.heavenhr.model.Offer;
import br.com.schumaker.heavenhr.repository.OfferRepository;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
public class OfferServiceTests {

	private Offer offerSr;
	private Offer offerJr;
	private List<Offer> listOffers;

	@Before
	public void initTestCase() {
		offerSr = new Offer();
		offerSr.setOfferId(1L);
		offerSr.setJobTitle("Java Sr. Developer");
		offerSr.setStartDate(new Date());
		offerSr.setNumberOfApplication(0);
		
		offerJr = new Offer();
		offerJr.setOfferId(2L);
		offerJr.setJobTitle("Java Jr. Developer");
		offerJr.setStartDate(new Date());
		offerJr.setNumberOfApplication(1);
		
		listOffers = Arrays.asList(offerSr, offerJr);
	}

	@Test
	public void testGetById() {
		OfferRepository offerRepository = Mockito.mock(OfferRepository.class);
		Mockito.when(offerRepository.findOne(Mockito.anyLong())).thenReturn(offerSr);
		OfferService offerService = new OfferService();
		offerService.offerRepository = offerRepository;
		Offer o = offerService.getById(1L);
		Assert.assertEquals(o.getJobTitle(),offerSr.getJobTitle());
	}
	
	@Test
	public void testListAll() {
		OfferRepository offerRepository = Mockito.mock(OfferRepository.class);
		Mockito.when(offerRepository.findAll()).thenReturn(listOffers);
		
		OfferService offerService = new OfferService();
		offerService.offerRepository = offerRepository;
		List<Offer> list = offerService.listAll();
		Assert.assertEquals(list.size(), listOffers.size());	
	}
}
