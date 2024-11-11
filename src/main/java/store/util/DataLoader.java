package store.util;

import store.domain.Product;
import store.domain.Promotion;

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
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                int buy = Integer.parseInt(fields[1]);
                int get = Integer.parseInt(fields[2]);
                LocalDate startDate = LocalDate.parse(fields[3]);
                LocalDate endDate = LocalDate.parse(fields[4]);

                Promotion promotion = new Promotion(name, startDate, endDate, buy, get);
                promotions.add(promotion);
            }
        } catch (IOException e) {
            System.out.println("[ERROR] promotions.md를 읽어오는데 문제가 발생했습니다. " + e.getMessage());
        }

        return promotions;
    }

    public List<Product> loadProducts(String filePath, List<Promotion> promotions) {
        List<Product> products = new ArrayList<>();

        Map<String, Promotion> promotionMap = new HashMap<>();
        for (Promotion promo : promotions) {
            promotionMap.put(promo.getName(), promo);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                int price = Integer.parseInt(fields[1]);
                int quantity = Integer.parseInt(fields[2]);
                String promotionName = fields[3].equals("null") ? null : fields[3];

                Promotion promotion = promotionName != null ? promotionMap.get(promotionName) : null;

                Product product = new Product(name, price, quantity, promotion);
                products.add(product);
            }
        } catch (IOException e) {
            System.out.println("[ERROR] products.md를 읽어오는데 문제가 발생했습니다." + e.getMessage());
        }
        return products;
    }

}
