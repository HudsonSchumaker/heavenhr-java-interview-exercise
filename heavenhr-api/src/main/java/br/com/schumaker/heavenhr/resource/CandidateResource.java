package br.com.schumaker.heavenhr.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.schumaker.heavenhr.model.Candidate;
import br.com.schumaker.heavenhr.service.CanditateService;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@RestController
@RequestMapping("/api/candidate/")
public class CandidateResource {

	@Autowired
	private CanditateService canditateService;
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<?> getCandidate(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(canditateService.getById(id));
    }
	
	@PostMapping(value = "/")
    public ResponseEntity<Void> saveCandidate(@Valid @RequestBody Candidate candidate){
			candidate = canditateService.saveCandidate(candidate);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(candidate.getCanditadeId()).toUri();
		
		return ResponseEntity.created(uri).build();
    }
}
