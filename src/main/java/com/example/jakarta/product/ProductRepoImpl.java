package com.example.jakarta.product;



import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.*;
@Path("products")
public class ProductRepoImpl implements ProductRepo{
     static HashSet<Product> products = new HashSet<>();


    public ProductRepoImpl(){
        products.add(new Product("tea", 4));
        products.add(new Product("sugar", 20));
        products.add(new Product("rice", 10));
        products.add(new Product("salt", 2));
    }
    @Path("getProduct")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Optional<Product> getProduct(@QueryParam("name")String name) {
        Product searchProduct=new Product(name,0);
        return products.stream().
                filter(product -> product.equals(searchProduct))
                .findFirst();
    }
    @GET
    @Path("addProduct")
    @Produces({MediaType.APPLICATION_JSON})
    public String addProduct( @QueryParam("name") String name,
                               @QueryParam("price") float price) {

      if(products.add(new Product(name,price))){
          return "product added successfully";
      }
      return "product already Exists";

    }

    @Override
    @GET
    @Path("updateProduct")
    @Produces({MediaType.APPLICATION_JSON})
    public String updateProduct(@QueryParam("name") String name,
                                @QueryParam("price") float price) {
        if(deleteProduct(name)){
            addProduct(name,price);
            return "Product updated successfully";
        }
        return "There is no such a product";
    }

    @GET
    @Path("deleteProduct")
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Boolean deleteProduct(@QueryParam("name")String name) {
        return products.remove(new Product(name,0));
    }
    public void printProducts(){
        products.forEach(System.out::println);
    }
}
