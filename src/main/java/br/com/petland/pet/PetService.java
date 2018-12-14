package br.com.petland.pet;

public class PetService {

	private DataRepository<Pet> repository;
	
	public PetService(DataRepository<Pet> repository) {
		this.repository = repository;
	}
	
	public Pet getPet(int id) {
		// TODO Auto-generated method stub
		return repository.getById(id);
	}

}
