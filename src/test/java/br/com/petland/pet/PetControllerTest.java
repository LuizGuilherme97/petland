package br.com.petland.pet;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;
import br.com.petland.pet.Pet;
import br.com.petland.pet.PetController;
import br.com.petland.pet.PetService;
import spark.Request;
import spark.Response;

public class PetControllerTest {
	
	@Test
	public void shouldGetPetFromService() {
		Pet expected = Pet.builder().id(1L).name("Luphie").age(12).sex("female").build();
		PetService service = mock(PetService.class);
		Request request = mock(Request.class);
		Response response = mock(Response.class);
		
		when(service.getPet(1L)).thenReturn(expected);
		when(request.params("id")).thenReturn("1");
		PetController controller = new PetController(service);
		
		assertThat(controller.getPet(request, response), is(expected));
	}
}
