package br.com.petland;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;

import br.com.petland.pet.DataRepository;
import br.com.petland.pet.Pet;
import br.com.petland.pet.PetController;
import br.com.petland.pet.PetDataRepository;
import br.com.petland.pet.PetService;
import xyz.morphia.Datastore;

public class PetModuleForTest extends AbstractModule{

    @Override
    protected void configure() {
        bind(Datastore.class).toInstance(new RepositoryHelper().getDataStore());
        // bind(RepositoryHelper.class);

        try {
            bind(new TypeLiteral<DataRepository<Pet>>() {
            }).toConstructor(PetDataRepository.class.getConstructor(Datastore.class));
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   
        bind(PetService.class);
        bind(PetController.class);
    }

}