package store.util;

import store.domain.Product;
import store.domain.Promotion;
import store.view.OutputView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {

    public List<Promotion> loadPromotions(String filePath) {
        List<Promotion> promotions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            readPromotionLines(br, promotions);
        } catch (IOException e) {
            OutputView.printError("promotions.md를 읽어오는데 문제가 발생했습니다.", e);
        }
        return promotions;
    }

    private void readPromotionLines(BufferedReader br, List<Promotion> promotions) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            promotions.add(createPromotion(line));
        }
    }

    private Promotion createPromotion(String line) {
        String[] fields = line.split(",");
        String name = fields[0];
        int buy = Integer.parseInt(fields[1]);
        int get = Integer.parseInt(fields[2]);
        LocalDate startDate = LocalDate.parse(fields[3]);
        LocalDate endDate = LocalDate.parse(fields[4]);

        return new Promotion(name, startDate, endDate, buy, get);
    }

    public List<Product> loadProducts(String filePath, List<Promotion> promotions) {
        List<Product> products = new ArrayList<>();
        Map<String, Promotion> promotionMap = createPromotionMap(promotions);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            readProductLines(br, products, promotionMap);
        } catch (IOException e) {
            OutputView.printError("products.md를 읽어오는데 문제가 발생했습니다.", e);
        }
        return products;
    }

    private void readProductLines(BufferedReader br, List<Product> products, Map<String, Promotion> promotionMap) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            products.add(createProduct(line, promotionMap));
        }
    }

    private Map<String, Promotion> createPromotionMap(List<Promotion> promotions) {
        Map<String, Promotion> promotionMap = new HashMap<>();
        for (Promotion promo : promotions) {
            promotionMap.put(promo.getName(), promo);
        }
        return promotionMap;
    }

    private Product createProduct(String line, Map<String, Promotion> promotionMap) {
        String[] fields = line.split(",");
        String name = fields[0];
        int price = Integer.parseInt(fields[1]);
        int quantity = Integer.parseInt(fields[2]);
        Promotion promotion = fields[3].equals("null") ? null : promotionMap.get(fields[3]);

        return new Product(name, price, quantity, promotion);
    }
}
