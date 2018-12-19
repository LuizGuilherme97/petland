package br.com.petland.pet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spark.Request;
import spark.Response;

public class PetController {
	
	PetService petService;
	
	public PetController(PetService petService) {
		// TODO Auto-generated constructor stub
		this.petService =  petService;
	}

	public PetController() {
		this.petService = new PetService();
	}

	public Pet getPet(Request request, Response response) {
		// TODO Auto-generated method stub
		// return this.petService.getPet(id);
		long id = Long.valueOf(request.params("id"));
		return petService.getPet(id);
	}

}
