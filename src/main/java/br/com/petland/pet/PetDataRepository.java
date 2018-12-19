package br.com.petland.pet;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;

public class PetDataRepository implements DataRepository<Pet> {

	private Datastore datastore;
    private MongoServer server;
    private MongoClient mongoClient;

    public PetDataRepository(Datastore datastore) {
        this.datastore = datastore;
    }

    public PetDataRepository() {
        Morphia morphia = new Morphia();

		server = new MongoServer(new MemoryBackend());
		ServerAddress serverAddress = new ServerAddress(server.bind());

        morphia.mapPackage("br.com.petland");

        mongoClient = new MongoClient(serverAddress);
        Datastore datastore = morphia.createDatastore(mongoClient, "integration_test");
		datastore.ensureIndexes();
        this.datastore = datastore;
    }

    @Override
    public Pet getById(Long id) {
        return datastore.get(Pet.class, id);
    }

}