package br.com.petland.pet;

public interface DataRepository<T> {

	public T getById(int id);

}