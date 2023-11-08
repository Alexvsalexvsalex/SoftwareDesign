package handler;

import com.mongodb.rx.client.MongoDatabase;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import rx.Observable;

public interface Handler {
    Observable<String> handle(HttpServerRequest<ByteBuf> req);
}
