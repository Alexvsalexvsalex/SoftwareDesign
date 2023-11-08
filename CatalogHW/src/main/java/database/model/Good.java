package database.model;

import org.bson.Document;

import java.util.Map;

/**
 * @author akirakozov
 */
public class Good {
    public final int id;
    public final String name;

    public final double price;

    public Good(Document doc) {
        this(doc.getInteger("id"), doc.getString("name"), doc.getDouble("price"));
    }

    public Good(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Document toDocument() {
        return new Document(Map.of(
                "id", id,
                "name", name,
                "price", price
        ));
    }

    public String toString(Currency currency) {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + Currency.convertFromDefaultCurrency(currency, price) + ' ' + currency + '\'' +
                '}';
    }
}
