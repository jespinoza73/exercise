 package services;

import org.jongo.Jongo;
import com.mongodb.DB;

 public class DBHandler extends Jongo{
        public DBHandler(DB db){
            super(db);
        }
 }