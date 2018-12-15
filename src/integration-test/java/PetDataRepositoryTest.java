import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.petland.pet.DataRepository;
import br.com.petland.pet.Pet;
import br.com.petland.pet.PetDataReposiory;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;

public class PetDataRepositoryTest {
	
	private RepositoryHelper repositoryHelper;
	private MongoServer server;
	private DataRepository<Pet> repository;

	@Before
    public void setUp() {
		Morphia morphia = new Morphia();

		server = new MongoServer(new MemoryBackend());
		ServerAddress serverAddress = new ServerAddress(server.bind());

        morphia.mapPackage("br.com.petland");

        Datastore datastore = morphia.createDatastore(new MongoClient(serverAddress), "integration_test");
		datastore.ensureIndexes();
		
		repositoryHelper = new RepositoryHelper(datastore);
		repository = new PetDataReposiory(datastore);

    }

    @After
    public void tearDown() {
        repositoryHelper.closeConnection();
        server.shutdown();
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
