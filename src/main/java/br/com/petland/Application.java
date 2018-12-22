package br.com.petland;

import static spark.Spark.*;

import javax.inject.Inject;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.petland.pet.PetController;

public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Inject
    private PetController controller;

    public Application() {
        Injector injector = Guice.createInjector(new PetModule());
        injector.injectMembers(this);
    }

    public Application(boolean testing) {
        Injector injector = Guice.createInjector(new PetModuleForTest());
        injector.injectMembers(this);
    }

    public void start() {
        port(8080);
        init();
        awaitInitialization();
        routes();
    }

    public void routes() {
        logger.info("Controller = ", controller.toString());
        get("/pet/:id", (req, res) -> {
            return controller.getPet(req, res);
            // return "OK";
        });
    }

    public void stop() {
        stop();
    }

    public static void main(String[] args) {
        new Application().start();
    }
}