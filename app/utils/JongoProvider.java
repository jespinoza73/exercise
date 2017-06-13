package utils;

import org.jongo.Jongo;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import play.Configuration;
import com.google.inject.Provider;
import javax.inject.Inject;

/**
* This class provides a Jongo instance properly instantiated.
* It reads mongodb parameters from application.conf.
* This class is used in Module.class
*/
public class JongoProvider implements Provider<Jongo>{

    private final static String  dbNameDefault = "test";
    private final static Integer dbPortDefault = 27017;
    private final static String  dbHostDefault = "localhost";
    
    private final Configuration configuration;

     @Inject
    public JongoProvider(Configuration configuration){
        this.configuration = configuration;
    }

	public Jongo get() {

        String dbname = configuration.getString("mongodb.dbname", dbNameDefault);
        String dbhost = configuration.getString("mongodb.dbhost", dbHostDefault);
        Integer dbport = configuration.getInt("mongodb.dbport", dbPortDefault);

        DB db = new MongoClient(dbhost, dbport).getDB(dbname);
        return new Jongo(db);
    }

}
		