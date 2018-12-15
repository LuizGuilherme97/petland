package br.com.petland.pet;

public class PetController {
	
	PetService petService;
	
	public PetController(PetService petService) {
		// TODO Auto-generated constructor stub
		this.petService =  petService;
	}

	public Pet getPet(Long id) {
		// TODO Auto-generated method stub
		return this.petService.getPet(id);
	}

}
