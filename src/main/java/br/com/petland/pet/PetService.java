package br.com.petland.pet;

public class PetService {

	private PetDataRepository repository;
	
	public PetService(PetDataRepository repository) {
		this.repository = repository;
	}
	
	public Pet getPet(int id) {
		// TODO Auto-generated method stub
		return repository.getPet(id);
	}

}
