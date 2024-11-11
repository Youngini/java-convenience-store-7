package store.domain;

public class Product {
    private String name;
    private int price;
    private int quantity;
    private Promotion promotion;

    public Product(String name, int price, int quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public Promotion getPromotion() {
        return this.promotion;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public void reduceQuantity(int requestedQuantity) {
        this.quantity -= requestedQuantity;
    }

    public boolean hasPromotion() {
        return this.promotion != null;
    }

    public int getPromotionStock() {
        return this.quantity;
    }

    public void reducePromotionStock(int totalFreeItems) {
        this.quantity -= totalFreeItems;
    }
}
