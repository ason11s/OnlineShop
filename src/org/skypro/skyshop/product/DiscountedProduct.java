package org.skypro.skyshop.product;

public class DiscountedProduct extends Product{
    private int basicPrice;
    private int discountPercent;

    public DiscountedProduct(String name, int basicPrice, int discountPercent){
        super(name);
        if (basicPrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0.");
        }
        if(discountPercent < 0 || discountPercent > 100){
            throw new IllegalArgumentException("Скидка должна быть в диапазоне от 0 до 100 включительно.");
        }
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
