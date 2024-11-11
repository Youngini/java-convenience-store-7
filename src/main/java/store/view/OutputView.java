package store.view;

import store.domain.Product;
import java.util.List;

public class OutputView {

    public static void printWelcomeMessage() {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\n");
    }

    public static void printProducts(List<Product> products) {
        for (Product product : products) {
            printProduct(product);
        }
    }

    private static void printProduct(Product product) {
        String promotionName = getPromotionName(product);
        String stockStatus = getStockStatus(product);

        System.out.printf("- %s %,d원 %s %s%n",
                product.getName(),
                product.getPrice(),
                stockStatus,
                promotionName);
    }

    private static String getPromotionName(Product product) {
        if (product.getPromotion() == null) {
            return "";
        }
        return product.getPromotion().getName();
    }

    private static String getStockStatus(Product product) {
        if (product.getQuantity() <= 0) {
            return "재고 없음";
        }
        return product.getQuantity() + "개";
    }

    public static void printError(String message, Exception e) {
        System.out.println("[ERROR] " + message + " - " + e.getMessage());
    }
}
