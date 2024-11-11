package store.domain;

import java.util.Optional;

public class Product {
    private String name;
    private int price;
    private int quantity;
    private Optional<Promotion> promotion;

    public Product(String name, int price, int quantity, Optional<Promotion> promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }
}
