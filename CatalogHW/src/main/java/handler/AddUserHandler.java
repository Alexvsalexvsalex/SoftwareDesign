package handler;

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

public class AddUserHandler implements Handler {
    private UserRepository userRepository;

    public AddUserHandler(MongoDatabase mongoDatabase) {
        this.userRepository = new UserRepository(mongoDatabase);
    }

    @Override
    public Observable<String> handle(HttpServerRequest<ByteBuf> req) {
        Map<String, List<String>> params = req.getQueryParameters();
        int id = Integer.parseInt(params.get("id").get(0));
        String login = params.get("login").get(0);
        String currency = params.get("currency").get(0);
        User user = new User(id, login, currency);
        return userRepository.insertUser(user).flatMap(result -> {
            if (result) {
                return Observable.just(user + " is added");
            } else {
                return Observable.just(user + " is not added");
            }
        });
    }
}
