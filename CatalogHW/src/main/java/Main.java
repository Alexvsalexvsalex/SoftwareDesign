import com.mongodb.rx.client.MongoDatabase;
import database.MongoDB;
import handler.*;
import io.reactivex.netty.protocol.http.server.HttpServer;

import java.util.Map;

public class Main {
    private static final MongoDatabase mongoDatabase = MongoDB.database;
    private static final Handler notFoundHandler = new NotFoundHandler();
    private static final Map<String, Handler> handlers = Map.of(
        "/add_good", new AddGoodHandler(mongoDatabase),
        "/user", new UserHandler(mongoDatabase),
        "/add_user", new AddUserHandler(mongoDatabase),
        "/goods", new GoodsHandler(mongoDatabase)
    );

    public static void main(String[] args) {
        HttpServer
                .newServer(8080)
                .start((req, resp) -> {
                    String path = req.getDecodedPath();
                    Handler handler = handlers.getOrDefault(path, notFoundHandler);
                    return resp.writeString(handler.handle(req));
                })
                .awaitShutdown();
    }
}
