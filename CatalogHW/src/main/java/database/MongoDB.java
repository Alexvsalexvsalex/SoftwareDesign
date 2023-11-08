package database;

import com.mongodb.rx.client.MongoClients;
import com.mongodb.rx.client.MongoDatabase;

/**
 * @author akirakozov
 */
public class MongoDB {
    public static MongoDatabase database = createMongoDatabase();

    private static MongoDatabase createMongoDatabase() {
        return MongoClients.create("mongodb://localhost:27017").getDatabase("shop");
    }
}

