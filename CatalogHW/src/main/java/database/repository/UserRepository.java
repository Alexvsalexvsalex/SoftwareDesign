package database.repository;

import com.mongodb.rx.client.MongoCollection;
import com.mongodb.rx.client.MongoDatabase;
import database.MongoDB;
import database.model.Currency;
import database.model.Good;
import database.model.User;
import org.bson.Document;
import rx.Observable;

import static com.mongodb.client.model.Filters.eq;

public class UserRepository {
    private final MongoDatabase mongoDatabase;

    public UserRepository(MongoDatabase mongoClient) {
        this.mongoDatabase = mongoClient;
    }

    private MongoCollection<Document> getCollection() {
        return mongoDatabase.getCollection("users");
    }

    private Observable<User> getAllUsers() {
        return getCollection().find().toObservable().map(User::new);
    }

    public Observable<User> getUser(int id) {
        return getCollection()
                .find(eq("id", id))
                .toObservable()
                .map(User::new);
    }

    public Observable<Boolean> insertUser(User user) {
        return getUser(user.id).isEmpty().flatMap(no -> {
            if (no) {
                return getCollection().insertOne(user.toDocument()).map(g -> true);
            } else {
                return Observable.just(false);
            }
        });
    }
}
