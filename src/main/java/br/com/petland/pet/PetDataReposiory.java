package br.com.petland.pet;

import xyz.morphia.Datastore;

public class PetDataReposiory implements DataRepository<Pet> {

	private Datastore datastore;

    public PetDataReposiory(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public Pet getById(Long id) {
        return datastore.get(Pet.class, id);
    }

}