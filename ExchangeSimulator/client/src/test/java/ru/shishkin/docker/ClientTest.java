package ru.shishkin.docker;

import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.UncheckedIOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Testcontainers
public class ClientTest {
    private static final int EXCHANGE_PORT = 8080;
    private static final int CLIENT_PORT = 8090;

    @Container
    private static final GenericContainer<?> EXCHANGE =
            new FixedHostPortGenericContainer<>("exchange:1.0-SNAPSHOT")
            .withFixedExposedPort(EXCHANGE_PORT, EXCHANGE_PORT)
            .withExposedPorts(EXCHANGE_PORT);

    private ConfigurableApplicationContext clientServer;

    private String sendRequest(final int port, final String func, final String params) {
        return Utils.readAll("http://localhost:" + port + "/" + func + "?" + params);
    }

    @BeforeClass
    public static void mainSetUp() {
        EXCHANGE.start();
        assertTrue(EXCHANGE.isRunning());
        System.out.println(EXCHANGE.getHost());
        System.out.println(EXCHANGE.getFirstMappedPort());
    }

    @Before
    public void setUp() {
        clientServer = Application.run(new String[0]);

        sendRequest(EXCHANGE_PORT, "reset", "");

        sendRequest(EXCHANGE_PORT, "ipo", "name=pltr&count=100&price=10");
        sendRequest(EXCHANGE_PORT, "ipo", "name=nvda&count=400&price=280");
        sendRequest(EXCHANGE_PORT, "ipo", "name=ru093258293&count=5&price=1");
    }

    @After
    public void shutdown() {
        if (clientServer != null) {
            clientServer.close();
        }
    }

    private void deposit(final String account, final int value) {
        assertEquals("", sendRequest(CLIENT_PORT, "deposit", "account=" + account + "&money=" + value));
    }

    private void checkBalance(final String account, final int value) {
        assertEquals(Integer.toString(value), sendRequest(CLIENT_PORT, "balance", "account=" + account));
    }

    private String buyStock(final String account, final String name, final int count, final int price) {
        return sendRequest(CLIENT_PORT, "buy",
                "account=" + account + "&name=" + name + "&count=" + count + "&price=" + price);
    }

    private String sellStock(final String account, final String name, final int count, final int price) {
        return sendRequest(CLIENT_PORT, "sell",
                "account=" + account + "&name=" + name + "&count=" + count + "&price=" + price);
    }

    private String register(final String account) {
        return sendRequest(CLIENT_PORT, "register", "account=" + account);
    }

    private String getStocks(final String account) {
        return sendRequest(CLIENT_PORT, "stocks", "account=" + account);
    }

    private String getPrice(final String name) {
        return sendRequest(CLIENT_PORT, "getPrice", "name=" + name);
    }

    private void exchangeSetPrice(final String name, final int price) {
        sendRequest(EXCHANGE_PORT, "setPrice", "name=" + name + "&price=" + price);
    }

    @Test
    public void register() {
        String userName = "user";
        assertEquals("true", register(userName));
        assertEquals("", getStocks(userName));
        checkBalance(userName, 0);
        assertEquals("false", register(userName));
    }

    @Test
    public void deposit() {
        String userName = "user";
        assertEquals("true", register(userName));
        deposit(userName, 10000);
        checkBalance(userName, 10000);

        deposit(userName, -5000);
        checkBalance(userName, 5000);

        try {
            deposit(userName, -10000);
            Assert.fail();
        } catch (UncheckedIOException ignored) {

        }
        checkBalance(userName, 5000);
    }

    @Test
    public void buyStocks() {
        String userName = "user";
        assertEquals("true", register(userName));
        deposit(userName, 10000);

        buyStock(userName, "pltr", 2, 10);
        assertEquals("pltr: 2", getStocks(userName));
        checkBalance(userName, 9980);

        buyStock(userName, "ru093258293", 3, 1);
        assertEquals("pltr: 2; ru093258293: 3", getStocks(userName));
        checkBalance(userName, 9977);
    }

    @Test
    public void failBuyStocks() {
        String userName = "user";
        assertEquals("true", register(userName));
        deposit(userName, 10000);

        buyStock(userName, "pltr", 2, 15);
        assertEquals("", getStocks(userName));
        checkBalance(userName, 10000);
    }

    @Test
    public void buyStocksNotMoreThanExists() {
        String userName = "user";
        assertEquals("true", register(userName));
        deposit(userName, 10000);

        assertEquals("1", buyStock(userName, "ru093258293", 1, 1));
        assertEquals("ru093258293: 1", getStocks(userName));
        checkBalance(userName, 9999);

        assertEquals("4", buyStock(userName, "ru093258293", 1000, 1));
        assertEquals("ru093258293: 5", getStocks(userName));
        checkBalance(userName, 9995);
    }

    @Test
    public void sellStocks() {
        String userName = "user";
        assertEquals("true", register(userName));
        deposit(userName, 10000);

        assertEquals("2", buyStock(userName, "pltr", 2, 10));
        assertEquals("2", sellStock(userName, "pltr", 2, 10));

        assertEquals("", getStocks(userName));
        deposit(userName, 10000);
    }

    @Test
    public void failSellStocksWhenPriceChanged() {
        String userName = "user";
        assertEquals("true", register(userName));
        deposit(userName, 10000);

        assertEquals("2", buyStock(userName, "nvda", 2, 280));

        exchangeSetPrice("nvda", 270);

        assertEquals("0", sellStock(userName, "nvda", 2, 280));

        assertEquals("nvda: 2", getStocks(userName));
        deposit(userName, 9440);
    }

    @Test
    public void sellStocksWhenPriceChanged() {
        String userName = "user";
        assertEquals("true", register(userName));
        deposit(userName, 10000);

        assertEquals("2", buyStock(userName, "nvda", 2, 280));

        exchangeSetPrice("nvda", 270);

        assertEquals("270", getPrice("nvda"));
        assertEquals("2", sellStock(userName, "nvda", 2, 270));

        assertEquals("", getStocks(userName));
        deposit(userName, 9980);
    }
}
