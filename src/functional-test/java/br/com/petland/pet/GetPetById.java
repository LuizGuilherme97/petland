package br.com.petland.pet;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;

import static java.util.Arrays.asList;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.petland.Application;
import br.com.petland.PetModuleForTest;
import br.com.petland.RepositoryHelper;
import br.com.petland.Resource;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetPetById {
    
    private static MongoServer server;

	private static RepositoryHelper repositoryHelper;

	private static Application app;
        
    @BeforeClass
    public static void setup(){
        
        server = new MongoServer(new MemoryBackend());
        server.bind("localhost", 27017);
        
        repositoryHelper = new RepositoryHelper();

        app = new Application(true);
        app.start();

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Before
    public void setup2() {
        Injector injector = Guice.createInjector(new PetModuleForTest());
		injector.injectMembers(this);
    }

    @Test
    public void shouldReturnPetSuccessfully(){
        Pet expectedPet = Pet.builder().id(1L).name("Luphie").age(12).sex("female").build();
        repositoryHelper.insertPet(expectedPet);
        Response response = given().pathParams("id", 1).when().get("/pet/{id}");

        JsonPath jsonPathEvaluator = response.jsonPath();

        assertThat(jsonPathEvaluator.getString("status"), is("ok"));
        assertThat(jsonPathEvaluator.getInt("code"), is(200));
        assertThat(jsonPathEvaluator.getList("messages"), is(nullValue()));
        assertThat(Pet.fromJson(jsonPathEvaluator.getJsonObject("data").toString()), is(expectedPet));
    }

    @Test
    public void shouldNotFoundPetSuccessfully(){
        Response response = given().pathParams("id", 1).when().get("/pet/{id}");

        JsonPath jsonPathEvaluator = response.jsonPath();

        assertThat(jsonPathEvaluator.getString("status"), is("not_found"));
        assertThat(jsonPathEvaluator.getInt("code"), is(404));
        assertThat(jsonPathEvaluator.getList("messages"), is(asList("Pet not found.")));
        assertThat(jsonPathEvaluator.getJsonObject("data"), is(nullValue()));
    }

    @Test
    public void shouldSomeShitHappened(){
        server.shutdown();
        Response response = given().pathParams("id", 1).when().get("/pet/{id}");

        JsonPath jsonPathEvaluator = response.jsonPath();

        assertThat(jsonPathEvaluator.getString("status"), is("error"));
        assertThat(jsonPathEvaluator.getInt("code"), is(500));
        assertThat(jsonPathEvaluator.getList("messages"), is(asList("Something crazy hapenned. Help!")));
        assertThat(jsonPathEvaluator.getJsonObject("data"), is(nullValue()));

        server.bind("localhost", 27017);
    }
}