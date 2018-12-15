import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;

import br.com.petland.pet.DataRepository;
import br.com.petland.pet.Pet;
import br.com.petland.pet.PetService;

public class PetServiceTest {
	
	@Test
	public void shouldGetPetFromRepository() {
		Long id = new Long(1);
		DataRepository<Pet> repository = mock(DataRepository.class);
		PetService service = new PetService(repository);
		Pet expectedPet = Pet.builder().id(id).name("luphie").age(2).sex("femea").build();
		when(repository.getById(id)).thenReturn(expectedPet);
		
		assertThat(service.getPet(id), is(expectedPet));
		verify(repository).getById(id);
	}

	@Test
	public void shouldReturnNullWhenNotFindPetFromRepository() {
		Long UNKNOWN_ID = new Long(-1);
		DataRepository<Pet> repository = mock(DataRepository.class);
		PetService service = new PetService(repository);
		Pet expectedPet = Pet.builder().id(UNKNOWN_ID).name("luphie").age(2).sex("femea").build();
		when(repository.getById(UNKNOWN_ID)).thenReturn(null);
		
		assertThat(service.getPet(UNKNOWN_ID), is(nullValue()));
		verify(repository).getById(UNKNOWN_ID);
	}

}
