package database.repository;

import com.mongodb.rx.client.MongoCollection;
import com.mongodb.rx.client.MongoDatabase;
import database.model.Currency;
import database.model.Good;
import org.bson.Document;
import rx.Observable;

import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class GoodRepository {
    private final MongoDatabase mongoDatabase;

    public GoodRepository(MongoDatabase mongoClient) {
        this.mongoDatabase = mongoClient;
    }

    private MongoCollection<Document> getCollection() {
        return mongoDatabase.getCollection("goods");
    }

    public Observable<Good> getAllGoods() {
        return getCollection().find().toObservable().map(Good::new);
    }

    public Observable<Good> getGood(int id) {
        return getCollection()
                .find(eq("id", id))
                .toObservable()
                .map(Good::new);
    }

    public Observable<Boolean> insertGood(Good good) {
        return getGood(good.id).isEmpty().flatMap(no -> {
            if (no) {
                return getCollection().insertOne(good.toDocument()).map(g -> true);
            } else {
                return Observable.just(false);
            }
        });
    }
}
