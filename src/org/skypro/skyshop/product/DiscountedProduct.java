package org.skypro.skyshop.product;

public class DiscountedProduct extends Product{
    private int basicPrice;
    private int discountPercent;

    public DiscountedProduct(String name, int basicPrice, int discountPercent){
        super(name);
        this.basicPrice = basicPrice;
        this.discountPercent = discountPercent;
    }
    @Override
    public int getPrice(){
        return basicPrice * (100 - discountPercent)/100;
    }
    @Override
    public boolean isSpecial(){
        return true;
    }

    @Override
    public String toString(){
        return getName() + ":" + getPrice() + "(" + discountPercent + "%)";
    }
}
