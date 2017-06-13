import com.google.inject.AbstractModule;

import org.jongo.Jongo;
import utils.JongoProvider;

import services.UserService;
import services.UserServiceImpl;
/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 *
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule {


    @Override
    public void configure() {
    
        // Use JongoProvider to provide Jongo instance already configured with db params
        bind(Jongo.class).toProvider( JongoProvider.class);

        bind(UserService.class).to( UserServiceImpl.class);
        
    }

}
