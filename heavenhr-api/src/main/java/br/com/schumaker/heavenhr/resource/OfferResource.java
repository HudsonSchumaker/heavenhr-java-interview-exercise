package br.com.schumaker.heavenhr.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.schumaker.heavenhr.model.Offer;
import br.com.schumaker.heavenhr.service.OfferService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@RestController
@RequestMapping("/api/offer/")
@ApiResponses({
@ApiResponse(code = 409, message = "Offer already exists.")})
public class OfferResource {

	@Autowired
	private OfferService offerService;
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<?> getOffer(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(offerService.getById(id));
    }
	
	@GetMapping(value = "/")
    public ResponseEntity<List<Offer>> listOffers(){
        return ResponseEntity.status(HttpStatus.OK).body(offerService.listAll());
    }
	
	@GetMapping(value = "/{page}/{count}")
    public Page<Offer> pageableList(@PathVariable int page, @PathVariable int count){
        return offerService.pageableList(page, count);
    }
	
	@PostMapping(value = "/")
    public ResponseEntity<Void> saveOffer(@Valid @RequestBody Offer offer){
        offer = offerService.saveOffer(offer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(offer.getOfferId()).toUri();
		
		return ResponseEntity.created(uri).build();
    }
}
