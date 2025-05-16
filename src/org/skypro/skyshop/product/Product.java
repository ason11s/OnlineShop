package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();
    public boolean isSpecial(){
        return false;
    }
    @Override
    public String toString(){
        return name + ":" + getPrice();
    }
    @Override
    public String getSearchTerm(){
        return name;
    }
    @Override
    public String getType(){
        return "PRODUCT";
    }
}
