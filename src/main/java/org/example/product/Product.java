package org.example.product;

import java.util.Objects;

public class Product {
    private String name;
    private float price;

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return name.equalsIgnoreCase(product.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.name.toLowerCase());
    }

    @Override
    public String toString() {
        return "product name: "+name+" product price: "+price;
    }
}
