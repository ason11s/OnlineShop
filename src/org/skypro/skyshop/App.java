package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

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

    }
}