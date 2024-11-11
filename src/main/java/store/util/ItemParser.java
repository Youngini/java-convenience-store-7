package store.util;

import java.util.ArrayList;
import java.util.List;

public class ItemParser {

    public static List<String> parseItems(String input) {
        Validator.validateInputFormat(input);

        List<String> items = new ArrayList<>();
        String[] itemArray = input.replace("[", "").split("],");

        for (String item : itemArray) {
            items.add(item.replace("]", "").trim());
        }
        return items;
    }
}
