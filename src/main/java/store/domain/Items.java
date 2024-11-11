package store.domain;

import java.util.List;

public class Items {
    private List<Item> items;

    public Items(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}
