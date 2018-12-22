package br.com.petland.pet;

import com.google.inject.ImplementedBy;

// @ImplementedBy(PetDataRepository.class)
public interface DataRepository<T> {

	public T getById(Long id);

}
