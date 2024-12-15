package org.example.productsManage;

import org.example.product.Product;
import org.example.productPageServlet.ProductsServlets;

import javax.jws.Oneway;
import java.util.*;

public class ProductRepoImpl implements ProductRepo{
    HashSet<Product> products = new HashSet<>();


    public ProductRepoImpl(){
        products.add(new Product("tea", 4));
        products.add(new Product("sugar", 20));
        products.add(new Product("rice", 10));
        products.add(new Product("salt", 2));
    }

    @Override
    public Optional<Product> getProduct(String name) {
        Product searchProduct=new Product(name,0);
        return products.stream().
                filter(product -> product.equals(searchProduct))
                .findFirst();
    }

    @Override
    public Boolean addProduct(Product product) {

      return products.add(product);

    }

    @Override
    public Boolean updateProduct(Product product) {
        if(deleteProduct(product.getName())){
            return addProduct(product);
        }
        return false;
    }

    @Override
    public Boolean deleteProduct(String name) {
        return products.remove(new Product(name,0));
    }
    public void printProducts(){
        products.forEach(System.out::println);
    }
}
