import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mongodb.ServerAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.petland.PetModule;
import br.com.petland.PetModuleForTest;
import br.com.petland.RepositoryHelper;
import br.com.petland.pet.Pet;
import br.com.petland.pet.PetService;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

public class PetServiceIT {
	
	private RepositoryHelper repositoryHelper;

	@Inject
	private PetService service;

	private MongoServer server;

	@Before
    public void setUp() {
		Injector injector = Guice.createInjector(new PetModuleForTest());
		injector.injectMembers(this);
		server = new MongoServer(new MemoryBackend());
		server.bind("localhost", 27017);
		
		repositoryHelper = new RepositoryHelper();
    }

    @After
    public void tearDown() {
        server.shutdown();
    }

	@Test
	public void shouldGetPet() {
		long id = 1;
		Pet pet = Pet.builder().id(id).name("Luphie").age(12).sex("female").build();

		repositoryHelper.insertPet(pet);
		assertThat(service.getPet(id), is(pet));

    }

	@Test
	public void shouldGetNullWhenPetNotFound() {
		long id = 1;
		Pet pet = Pet.builder().id(id).name("Luphie").age(12).sex("female").build();

		repositoryHelper.insertPet(pet);
		
		assertThat(service.getPet(-1L), is(nullValue()));
	}
}
