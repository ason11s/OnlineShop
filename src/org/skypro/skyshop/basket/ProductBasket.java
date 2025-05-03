package org.skypro.skyshop.basket;
import java.util.List;
import java.util.ArrayList;
import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private List<Product> products = new ArrayList<>();
    public void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for(Product product :products){
            totalPrice += product.getPrice();
        }
        return totalPrice;

    }
}
