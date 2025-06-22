package org.skypro.skyshop.basket;
import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ProductBasket {
    private Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        String name = product.getName().toLowerCase();
        products.computeIfAbsent(name, k -> new ArrayList<>()).add(product);
    }

    public int getTotalPrice() {
        return products.values().stream()
                .flatMap(List::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }
    private long getSpecialCount() {
        return products.values().stream()
                .flatMap(List::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        products.values().stream()
                .flatMap(List::stream)
                .forEach(System.out::println);

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + getSpecialCount());
    }

    public boolean containsProduct(String name) {
        if (name == null){
            return false;
        }
       List<Product>productList = products.get(name.toLowerCase());
            return productList != null && !productList.isEmpty();
    }
    public void clearBasket() {
        products.clear();
    }

        public List<Product> removeProductByName(String name) {
            String key = name.toLowerCase();
            List<Product> removed = products.remove(key);
            return removed != null ? removed : new ArrayList<>();
        }
}
