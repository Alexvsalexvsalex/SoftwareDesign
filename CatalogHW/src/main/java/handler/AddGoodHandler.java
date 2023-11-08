package handler;

import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoDatabase;
import database.model.Good;
import database.repository.GoodRepository;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import rx.Observable;

import java.util.List;
import java.util.Map;

public class AddGoodHandler implements Handler {
    private GoodRepository goodRepository;

    public AddGoodHandler(MongoDatabase mongoDatabase) {
        this.goodRepository = new GoodRepository(mongoDatabase);
    }

    @Override
    public Observable<String> handle(HttpServerRequest<ByteBuf> req) {
        Map<String, List<String>> params = req.getQueryParameters();
        int id = Integer.parseInt(params.get("id").get(0));
        String name = params.get("name").get(0);
        double price = Double.parseDouble(params.get("price").get(0));
        Good good = new Good(id, name, price);
        return goodRepository.insertGood(good).flatMap(result -> {
            if (result) {
                return Observable.just(good + " is added");
            } else {
                return Observable.just(good + " is not added");
            }
        });
    }
}
