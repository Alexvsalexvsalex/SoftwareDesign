package handler;

import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoDatabase;
import database.model.Good;
import database.model.User;
import database.repository.GoodRepository;
import database.repository.UserRepository;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import rx.Observable;

import java.util.List;
import java.util.Map;

public class GoodsHandler implements Handler {
    private GoodRepository goodRepository;
    private UserRepository userRepository;


    public GoodsHandler(MongoDatabase mongoDatabase) {
        this.goodRepository = new GoodRepository(mongoDatabase);
        this.userRepository = new UserRepository(mongoDatabase);
    }

    @Override
    public Observable<String> handle(HttpServerRequest<ByteBuf> req) {
        Map<String, List<String>> params = req.getQueryParameters();
        int userId = Integer.parseInt(params.get("userId").get(0));
        Observable<User> user = userRepository.getUser(userId);
        Observable<Good> goods = goodRepository.getAllGoods();
        return user.isEmpty().flatMap(no -> {
            if (no) {
                return Observable.just(userId + " user not found");
            } else {
                return user.flatMap(u -> goods.map(g -> g.toString(u.currency)));
            }
        });
    }
}
