package ru.shishkin.docker;

import java.util.HashMap;
import java.util.Map;

public class ClientManager {
    private final Map<String, Account> clients = new HashMap<>();
    private final StockClient stockClient = new StockClient("localhost:8080");

    public synchronized boolean register(final String name) {
        return clients.putIfAbsent(name, new Account(name)) == null;
    }

    public synchronized int sell(final String account, final String name, final int count, final int price) {
        Account client = clients.get(account);
        assert client.getCount(name) >= count;
        int sellTry = stockClient.sell(name, count, price);
        client.changeCount(name, -sellTry);
        client.changeBalance(sellTry * price);
        return sellTry;
    }

    public synchronized int buy(final String account, final String name, final int count, final int price) {
        Account client = clients.get(account);
        int sumPrice = count * price;
        assert client.getBalance() >= sumPrice;
        int buyTry = stockClient.buy(name, count, price);
        client.changeCount(name, buyTry);
        client.changeBalance(-buyTry * price);
        return buyTry;
    }

    public synchronized void deposit(final String account, final int value) {
        Account client = clients.get(account);
        assert client.getBalance() >= -value;
        client.changeBalance(value);
    }

    public synchronized int getBalance(final String account) {
        Account client = clients.get(account);
        return client.getBalance();
    }

    public synchronized Map<String, Integer> getStocks(final String account) {
        Account client = clients.get(account);
        return client.getStocks();
    }
}
