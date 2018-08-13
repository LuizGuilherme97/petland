package petland;


import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

import br.com.petland.pet.Pet;
import br.com.petland.pet.PetController;
import br.com.petland.pet.PetService;

public class PetControllerTest {

	@Test
	public void shouldGetPet(){
		
		//arrange - set everything used on the test
		PetService petService = mock(PetService.class);
		PetController petController = new PetController(petService);
		Pet petExpected =  new Pet();
		when(petService.getPet(1)).thenReturn(petExpected);
		//act - execute what must be tested (the real action) and retuns the result.
		Pet result = petController.getPet(1);
		//assert -  verify if the results match with the expected result
		assertEquals(petExpected,result);
		verify(petService).getPet(1);
	}
	
}
