package store.service;

import store.domain.Item;
import store.domain.Items;
import store.domain.Product;

import java.util.Map;

public class ProductService {
    private Map<String, Product> products;

    public ProductService(Map<String, Product> products) {
        this.products = products;
    }

    // 1. 상품 재고 확인
    public void checkItemStock(Items items) {
        for (Item item : items.getItems()) {
            Product product = products.get(item.getName());
            if (product == null) {
                throw new IllegalArgumentException("[ERROR] 해당 상품이 존재하지 않습니다: " + item.getName());
            }
            int requestedQuantity = Integer.parseInt(item.getQuantity());
            if (product.getQuantity() == 0 || product.getQuantity() < requestedQuantity) {
                throw new IllegalArgumentException("[ERROR] 재고가 부족합니다: " + item.getName());
            }
        }
    }

    // 2. 재고 업데이트
    public void updateInventory(Items items) {
        for (Item item : items.getItems()) {
            Product product = products.get(item.getName());
            int requestedQuantity = Integer.parseInt(item.getQuantity());
            product.reduceQuantity(requestedQuantity);
        }
    }

}
