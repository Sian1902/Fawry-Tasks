package com.example.jakarta.product;



import java.util.Optional;

public interface ProductRepo {
    public Optional<Product> getProduct(String name);
    public String addProduct(String name,float price);
    public String updateProduct(String name,float price);
    public Boolean deleteProduct(String name);
}
