package br.com.petland.pet;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

import javax.inject.Inject;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;

public class PetDataRepository implements DataRepository<Pet> {

	private Datastore datastore;

    public PetDataRepository() {
        Morphia morphia = new Morphia();

        morphia.mapPackage("br.com.petland");

        MongoClient mongoClient = new MongoClient();
        Datastore datastore = morphia.createDatastore(mongoClient, "petland");
		datastore.ensureIndexes();
        this.datastore = datastore;
    }

    public PetDataRepository(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public Pet getById(Long id) {
        return datastore.get(Pet.class, id);
    }

}