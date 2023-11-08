package ru.shishkin.docker;

import org.springframework.web.util.UriComponentsBuilder;

public class StockClient {
    private final String baseUrl;

    public StockClient(final String url) {
        this.baseUrl = url;
    }

    public int getStockPrice(final String name) {
        String url = UriComponentsBuilder.newInstance().scheme("http").host(baseUrl).path("/getPrice")
                .queryParam("name", name).build().toUriString();
        return Integer.parseInt(Utils.readAll(url));
    }

    public int buy(final String name, final int count, final int price) {
        String url = UriComponentsBuilder.newInstance().scheme("http").host(baseUrl).path("/buy")
                .queryParam("name", name).queryParam("count", count)
                .queryParam("price", price).build().toUriString();
        return Integer.parseInt(Utils.readAll(url));
    }

    public int sell(final String name, final int count, final int price) {
        String url = UriComponentsBuilder.newInstance().scheme("http").host(baseUrl).path("/sell")
                .queryParam("name", name).queryParam("count", count)
                .queryParam("price", price).build().toUriString();
        return Integer.parseInt(Utils.readAll(url));
    }
}
