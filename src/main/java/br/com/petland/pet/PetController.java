package br.com.petland.pet;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.petland.Resource;
import lombok.NoArgsConstructor;
import spark.Request;
import spark.Response;

@NoArgsConstructor
public class PetController {
	
	private static Logger logger = LoggerFactory.getLogger(PetController.class);

	@Inject
	private PetService petService;
	
	public PetController(PetService petService) {
		this.petService =  petService;
	}

	public Resource<Pet> getPet(Request request, Response response) {
		
		logger.info("Get Pet by Id", request);
		
		try {
			long id = Long.valueOf(request.params("id"));
			Pet pet = petService.getPet(id);
	
			if (pet == null)
				return Resource.notFound(pet);
			
			return Resource.success(pet); 
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return Resource.error("Something crazy hapenned. Help!");
		}
	}

}
