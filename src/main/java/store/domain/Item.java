package store.domain;

public class Item {
    private String name;
    private String quantity;

    public Item(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }
}
