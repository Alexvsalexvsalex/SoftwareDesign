package handler;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import rx.Observable;

public class NotFoundHandler implements Handler {
    @Override
    public Observable<String> handle(HttpServerRequest<ByteBuf> req) {
        return Observable.just("Not found");
    }
}
