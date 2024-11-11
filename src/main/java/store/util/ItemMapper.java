package store.util;

import store.domain.Item;
import store.domain.Items;

import java.util.ArrayList;
import java.util.List;

public class ItemMapper {
    public static Items mapToItems(List<String> itemList) {
        List<Item> items = new ArrayList<>();
        for (String itemString : itemList) {
            items.add(parseItem(itemString));
        }
        return new Items(items);
    }

    private static Item parseItem(String itemString) {
        String[] parts = itemString.split("-");
        String name = parts[0].trim();
        String quantity = parts[1].trim();

        return new Item(name, quantity);
    }

}
