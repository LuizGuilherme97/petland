import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.petland.pet.DataRepository;
import br.com.petland.pet.Pet;
import br.com.petland.pet.PetDataRepository;

public class PetDataRepositoryTest {
	
	private RepositoryHelper repositoryHelper;
	private DataRepository<Pet> repository;

	@Before
    public void setUp() {
		
		repositoryHelper = new RepositoryHelper();
		repository = new PetDataRepository(repositoryHelper.getDataStore());

    }

    @After
    public void tearDown() {
        repositoryHelper.shutdown();
    }

	@Test
	public void shouldGetPetFromRepository() {
		long id = 1;
		Pet pet = Pet.builder().id(id).name("Luphie").age(12).sex("female").build();

		repositoryHelper.insertPet(pet);
		
		assertThat(repository.getById(id), is(pet));

		repositoryHelper.removePet(pet);
	}

	@Test
	public void shouldGetNullWhenPetNotFound() {
		long id = 1;
		long unknownId = -1;

		Pet pet = Pet.builder().id(id).name("Luphie").age(12).sex("female").build();

		repositoryHelper.insertPet(pet);
		
		assertThat(repository.getById(unknownId), is(nullValue()));

		repositoryHelper.removePet(pet);
	}

}
