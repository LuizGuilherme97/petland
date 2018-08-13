import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;

import br.com.petland.pet.Pet;
import br.com.petland.pet.PetDataRepository;
import br.com.petland.pet.PetService;

public class PetServiceTest {
	
	@Test
	public void shouldGetPetFromRepository() {
		PetDataRepository repository = mock(PetDataRepository.class);
		PetService service = new PetService(repository);
		Pet expectedPet = new Pet(1,"luphie",2,"femea");

		when(repository.getPet(1)).thenReturn(expectedPet);
		
		assertThat(service.getPet(1), is(expectedPet));
		verify(repository).getPet(1);
	}

}
