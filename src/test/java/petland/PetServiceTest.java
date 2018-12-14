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
		DataRepository<Pet> repository = mock(DataRepository.class);
		PetService service = new PetService(repository);
		Pet expectedPet = Pet.builder().id(1).name("luphie").age(2).sex("femea").build();
		when(repository.getById(1)).thenReturn(expectedPet);
		
		assertThat(service.getPet(1), is(expectedPet));
		verify(repository).getById(1);
	}

	@Test
	public void shouldReturnNullWhenNotFindPetFromRepository() {
		int UNKNOWN_ID = -1;
		DataRepository<Pet> repository = mock(DataRepository.class);
		PetService service = new PetService(repository);
		Pet expectedPet = Pet.builder().id(UNKNOWN_ID).name("luphie").age(2).sex("femea").build();
		when(repository.getById(UNKNOWN_ID)).thenReturn(null);
		
		assertThat(service.getPet(UNKNOWN_ID), is(nullValue()));
		verify(repository).getById(UNKNOWN_ID);
	}

}
