package br.com.schumaker.heavenhr.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.schumaker.heavenhr.model.Application;
import br.com.schumaker.heavenhr.model.EnumApplicationStatus;
import br.com.schumaker.heavenhr.service.ApplicationService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@RestController
@RequestMapping("/api/application/")
@ApiResponses({
@ApiResponse(code = 400, message = "The request was not corrected due to incorrect parameters.")})
public class ApplicationResource {

	@Autowired
	private ApplicationService applicationService;
	
	@GetMapping(value = "/")
    public ResponseEntity<List<Application>> listApplications(){
        return ResponseEntity.status(HttpStatus.OK).body(applicationService.listAll());
    }
	
	@GetMapping(value = "/offer/{id}")
    public ResponseEntity<List<Application>> listApplicationsByOffer(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(applicationService.listPerOffer(id));
    }
	
	@GetMapping(value = "/offer/{idOffer}/{idApp}")
    public ResponseEntity<?> applicationsByOffer(@PathVariable Long idOffer, @PathVariable Long idApp){
        return ResponseEntity.status(HttpStatus.OK).body((applicationService.appPerOffer(idOffer, idApp)));
    }
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<?> getApplication(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(applicationService.getById(id));
    }
	
	@GetMapping(value = "/{page}/{count}")
    public Page<Application> pageableList(@PathVariable int page, @PathVariable int count){
        return applicationService.pageableList(page, count);
    }
	
	@GetMapping(value = "/total")
    public ResponseEntity<?> countApplications(){
		Long count = applicationService.getNumberOfApplications();
		if(null == count) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(count);
    }
	
	@PostMapping(value = "/public/apply/")
    public ResponseEntity<Void> saveApplication(@Valid @RequestBody Application application){
		application.setStatus(EnumApplicationStatus.APPLIED);
		application = applicationService.saveApplication(application);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(application.getApplicationId()).toUri();
		
		return ResponseEntity.created(uri).build();
    }
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Void> progressStatus(@RequestBody String status, @PathVariable("id") Long id) {
		applicationService.progressStatus(status, id);
		return ResponseEntity.noContent().build();
	}
}
