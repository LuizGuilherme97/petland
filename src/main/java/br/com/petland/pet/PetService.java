package br.com.petland.pet;

import javax.inject.Inject;

public class PetService {

	@Inject
	private DataRepository<Pet> repository;
	
	public PetService(DataRepository<Pet> repository) {
		this.repository = repository;
	}

	public PetService() {
		this.repository = new PetDataRepository();
	}
	
	public Pet getPet(Long id) {
		// TODO Auto-generated method stub
		return repository.getById(id);
	}

}
