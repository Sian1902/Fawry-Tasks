package org.example.productsManage;

import org.example.product.Product;

import java.util.Optional;

public interface ProductRepo {
    public Optional<Product> getProduct(String name);
    public Boolean addProduct(Product product);
    public Boolean updateProduct(Product product);
    public Boolean deleteProduct(String name);
}
