package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.util.ItemParser;

import java.util.List;

public class InputView {

    public static List<String> readItems() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String input = Console.readLine();
        return ItemParser.parseItems(input);
    }

    public static String getUserResponse() {
        return Console.readLine().trim();
    }
}
