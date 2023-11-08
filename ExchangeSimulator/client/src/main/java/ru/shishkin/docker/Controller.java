package ru.shishkin.docker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class Controller {
    private final ClientManager clientManager = new ClientManager();
    private final StockClient stockClient = new StockClient("localhost:8080");

    @RequestMapping("/buy")
    public int buy(@RequestParam final String account, @RequestParam final String name,
                   @RequestParam final int count, @RequestParam final int price) {
        return clientManager.buy(account, name, count, price);
    }

    @RequestMapping("/register")
    public boolean register(@RequestParam final String account) {
        return clientManager.register(account);
    }

    @RequestMapping("/sell")
    public int sell(@RequestParam final String account, @RequestParam final String name,
                   @RequestParam final int count, @RequestParam final int price) {
        return clientManager.sell(account, name, count, price);
    }

    @RequestMapping("/deposit")
    public void setPrice(@RequestParam final String account, @RequestParam final int money) {
        clientManager.deposit(account, money);
    }

    @RequestMapping("/balance")
    public int balance(@RequestParam final String account) {
        return clientManager.getBalance(account);
    }

    @RequestMapping("/stocks")
    public String stocks(@RequestParam final String account) {
        return clientManager.getStocks(account).entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue()).sorted().collect(Collectors.joining("; "));
    }

    @RequestMapping("/getPrice")
    public int getPrice(@RequestParam final String name) {
        return stockClient.getStockPrice(name);
    }
}