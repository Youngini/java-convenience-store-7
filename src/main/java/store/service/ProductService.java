package store.service;

import camp.nextstep.edu.missionutils.DateTimes;
import store.domain.Item;
import store.domain.Items;
import store.domain.Product;
import store.domain.Promotion;
import store.view.InputView;
import store.view.OutputView;

import java.time.LocalDate;
import java.util.Map;

public class ProductService {
    private final Map<String, Product> products;

    public ProductService(Map<String, Product> products) {
        this.products = products;
    }

    // 1. 상품 재고 확인
    public void checkItemStock(Items items) {
        for (Item item : items.getItems()) {
            Product product = findProduct(item.getName());
            verifyStock(product, item);
        }
    }

    private Product findProduct(String productName) {
        Product product = products.get(productName);
        if (product == null) {
            throw new IllegalArgumentException("[ERROR] 해당 상품이 존재하지 않습니다: " + productName);
        }
        return product;
    }

    private void verifyStock(Product product, Item item) {
        int requestedQuantity = Integer.parseInt(item.getQuantity());
        if (product.getQuantity() < requestedQuantity) {
            throw new IllegalArgumentException("[ERROR] 재고가 부족합니다: " + item.getName());
        }
    }

    // 2. 재고 업데이트
    public void updateInventory(Items items) {
        for (Item item : items.getItems()) {
            Product product = findProduct(item.getName());
            int requestedQuantity = Integer.parseInt(item.getQuantity());
            product.reduceQuantity(requestedQuantity);
        }
    }

    // 3. 프로모션 적용 여부 확인
    public void applyPromotion(Items items) {
        for (Item item : items.getItems()) {
            Product product = findProduct(item.getName());
            if (product.hasPromotion() && isPromotionActive(product.getPromotion())) {
                int requestedQuantity = Integer.parseInt(item.getQuantity());
                handlePromotionApplication(product, product.getPromotion(), requestedQuantity);
            }
        }
    }

    private boolean isPromotionActive(Promotion promotion) {
        LocalDate today = DateTimes.now().toLocalDate();
        return !today.isBefore(promotion.getStartDate()) && !today.isAfter(promotion.getEndDate());
    }

    private void handlePromotionApplication(Product product, Promotion promotion, int requestedQuantity) {
        int totalFreeItems = calculateFreeItems(promotion, requestedQuantity);
        int availableFreeItems = Math.min(product.getPromotionStock(), totalFreeItems);

        if (availableFreeItems < totalFreeItems) {
            OutputView.askAdditionalPurchase(product.getName(), totalFreeItems - availableFreeItems);
            String response = InputView.getUserResponse();
            if (response.equalsIgnoreCase("Y")) {
                product.reducePromotionStock(availableFreeItems);
                return;
            }
        }
        product.reducePromotionStock(availableFreeItems);
        handleInsufficientPromotionStock(product, totalFreeItems, availableFreeItems);
    }

    private int calculateFreeItems(Promotion promotion, int requestedQuantity) {
        return (requestedQuantity / promotion.getRequiredQuantity()) * promotion.getFreeQuantity();
    }

    private void handleInsufficientPromotionStock(Product product, int totalFreeItems, int availableFreeItems) {
        if (totalFreeItems > availableFreeItems) {
            OutputView.askForFullPricePurchase(product.getName(), totalFreeItems - availableFreeItems);
            String response = InputView.getUserResponse();
            if (!response.equalsIgnoreCase("Y")) {

            }
        }
    }
}
