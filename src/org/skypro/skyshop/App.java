package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Product product1 = new SimpleProduct("йогурт", 50);
        Product product2 = new SimpleProduct("кефир", 39);
        Product product3 = new SimpleProduct("молоко", 45);
        Product product4 = new DiscountedProduct("сметана", 40, 10);
        Product product5 = new FixPriceProduct("сыр");
        Product product6 = new SimpleProduct("сливки", 35);

        ProductBasket basket = new ProductBasket();
        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);
        //basket.addProduct(product6);

        SearchEngine searchEngine = new SearchEngine();
        searchEngine.add(product1);
        searchEngine.add(product2);
        searchEngine.add(product3);
        searchEngine.add(product4);
        searchEngine.add(product5);

        Article article1 = new Article("Вкусный йогурт", "Йогрут улучшает пищеварение");
        Article article2 = new Article("Польза кефира", "Кефир содержит много белка");
        searchEngine.add(article1);
        searchEngine.add(article2);

        System.out.println("\nПоиск по слову 'йогурт':");
        printSearchResults(searchEngine.search("йогурт"));

        System.out.println("\nПоиск по слову 'молоко':");
        printSearchResults(searchEngine.search("молоко"));

        System.out.println("\nПоиск по слову 'белка':");
        printSearchResults(searchEngine.search("белка"));

        System.out.println("\nПоиск по слову 'шоколад':");
        printSearchResults(searchEngine.search("шоколад"));



        System.out.println("\nСодержимое корзины");
        basket.printBasket();

        System.out.println("\nОбщая стоимость корзины");
        System.out.println(basket.getTotalPrice() + "рублей");

        System.out.println("\nЕсть ли 'хлеб' в корзине?");
        System.out.println(basket.containsProduct("хлеб"));

        System.out.println("\nЕсть ли 'молоко' в корзине?");
        System.out.println(basket.containsProduct("молоко"));

        System.out.println("\nОчищаем корзину...");
        basket.clearBasket();

        System.out.println("\nСодержимое корзины после очистки:");
        basket.printBasket();

        System.out.println("\nОбщая стоимость пустой корзины:");
        System.out.println(basket.getTotalPrice() + " руб.");

        System.out.println("\nЕсть ли 'молоко' в пустой корзине?");
        System.out.println(basket.containsProduct("молоко"));

        System.out.println("Проверка валидации продуктов");
        try {
            Product InvalidProduct1 = new SimpleProduct(" ", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        try {
            Product invalidProduct2 = new SimpleProduct("Творог", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            Product invalidProduct3 = new DiscountedProduct("Масло", -20, 15);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            Product invalidProduct4 = new DiscountedProduct("Майонез", 50, 110);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            Product invalidProduct5 = new DiscountedProduct("Хлеб", 40, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nПоиск самого подходящего объекта:");

        try {
            Searchable bestMatch = searchEngine.findBestMatch("кефир");
            System.out.println("Лучший результат поиска: " + bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }

        try {
            Searchable bestMatch = searchEngine.findBestMatch("шоколад");
            System.out.println("Лучший результат поиска: " + bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }

        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);

        System.out.println("\nУдаление продукта 'кефир' из корзины:");
        List<Product> removedProducts = basket.removeProductByName("кефир");
        if (removedProducts.isEmpty()){
            System.out.println("Список пуст");
        } else {
            System.out.println("Удаленные продукты:");
            for (Product p : removedProducts){
                System.out.println(p);
            }
        }
        System.out.println("\nСодержимое корзины после удаления:");
        basket.printBasket();

        System.out.println("\nУдаление продукта 'лимонад' из корзины:");
        List<Product> removedProducts2 = basket.removeProductByName("лимонад");
        if (removedProducts2.isEmpty()){
            System.out.println("Список пуст");
        }else {
            System.out.println("Удаленные продукты:");
            for (Product p : removedProducts2){
                System.out.println(p);
            }
        }

        System.out.println("\nСодержимое корзины после повторного удаления:");
        basket.printBasket();

    }
    private static void printRemovedProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            for (Product p : products) {
                System.out.println(p);
            }
        }
    }
    private static void printSearchResults(List<Searchable> results) {
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            for (Searchable s : results) {
                System.out.println(s.getStringRepresentation());
            }
        }
    }
}