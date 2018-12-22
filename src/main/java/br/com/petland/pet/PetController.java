package br.com.petland.pet;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.NoArgsConstructor;
import spark.Request;
import spark.Response;

@NoArgsConstructor
public class PetController {
	
	private static Logger logger = LoggerFactory.getLogger(PetController.class);

	@Inject
	private PetService petService;
	
	public PetController(PetService petService) {
		// TODO Auto-generated constructor stub
		this.petService =  petService;
	}

	public Pet getPet(Request request, Response response) {
		// TODO Auto-generated method stub
		// return this.petService.getPet(id);
		logger.info("Get Pet by Id", request);
		long id = Long.valueOf(request.params("id"));
		return petService.getPet(id);
	}

}
