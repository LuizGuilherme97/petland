import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import br.com.petland.pet.Pet;
import xyz.morphia.Datastore;
import xyz.morphia.Key;
import xyz.morphia.Morphia;

public class RepositoryHelper {

    private MongoClient mongoClient;
    private Morphia morphia;
	private Datastore datastore;

    public RepositoryHelper(Datastore datastore){
        this.datastore = datastore;
    }

    public Key<Pet> insertPet(Pet pet){
        return datastore.save(pet);
    }

    public void removePet(Pet pet) {
        datastore.delete(pet);        
    }

    public void closeConnection() {
        // mongoClient.close();
    }
}