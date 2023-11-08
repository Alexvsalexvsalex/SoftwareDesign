package database.model;

import org.bson.Document;

import java.util.Map;

/**
 * @author akirakozov
 */
public class User {
    public final int id;
    public final String login;
    public final Currency currency;

    public User(Document doc) {
        this(doc.getInteger("id"), doc.getString("login"), doc.getString("currency"));
    }

    public User(int id, String login, String currency) {
        this(id, login, Currency.valueOf(currency));
    }

    public User(int id, String login, Currency currency) {
        this.id = id;
        this.login = login;
        this.currency = currency;
    }

    public Document toDocument() {
        return new Document(Map.of(
                "id", id,
                "login", login,
                "currency", currency.toString()
        ));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", currency='" + currency +
                '}';
    }
}
