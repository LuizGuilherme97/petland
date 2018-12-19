import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.petland.pet.Pet;
import br.com.petland.pet.PetDataRepository;
import br.com.petland.pet.PetService;

public class PetServiceIT {
	
	private RepositoryHelper repositoryHelper;
	private PetService service;

	@Before
    public void setUp() {
		repositoryHelper = new RepositoryHelper();
		service = new PetService(new PetDataRepository(repositoryHelper.getDataStore()));
    }

    @After
    public void tearDown() {
        repositoryHelper.shutdown();
    }

	@Test
	public void shouldGetPet() {
		long id = 1;
		Pet pet = Pet.builder().id(id).name("Luphie").age(12).sex("female").build();

		repositoryHelper.insertPet(pet);
		
		assertThat(service.getPet(id), is(pet));

		repositoryHelper.removePet(pet);
	}

	@Test
	public void shouldGetNullWhenPetNotFound() {
		long id = 1;
		Pet pet = Pet.builder().id(id).name("Luphie").age(12).sex("female").build();

		repositoryHelper.insertPet(pet);
		
		assertThat(service.getPet(-1L), is(nullValue()));
		repositoryHelper.removePet(pet);
	}
}
