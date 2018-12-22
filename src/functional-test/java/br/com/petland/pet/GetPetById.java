package br.com.petland.pet;

import static io.restassured.RestAssured.given;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.junit.Before;
import org.junit.Test;

import br.com.petland.Application;
import br.com.petland.PetModuleForTest;
import br.com.petland.RepositoryHelper;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import io.restassured.RestAssured;

public class GetPetById {
    
    private MongoServer server;

	private RepositoryHelper repositoryHelper;

	private Application app;
        
    @Before
    public void setup(){
        Injector injector = Guice.createInjector(new PetModuleForTest());
		injector.injectMembers(this);
  
        server = new MongoServer(new MemoryBackend());
        server.bind("localhost", 27017);
        
        repositoryHelper = new RepositoryHelper();

        app = new Application(true);
        app.start();

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void shouldReturn200(){
        
        repositoryHelper.insertPet(Pet.builder().id(1L).name("Luphie").age(12).sex("female").build());
        given().pathParams("id", 1).when().get("/pet/{id}").then().statusCode(200);
    }

    

}