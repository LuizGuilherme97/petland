package br.com.petland.pet;

public class PetService {

	private DataRepository<Pet> repository;
	
	public PetService(DataRepository<Pet> repository) {
		this.repository = repository;
	}
	
	public Pet getPet(Long id) {
		// TODO Auto-generated method stub
		return repository.getById(id);
	}

}
