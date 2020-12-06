package com.example.Ecosphere.Product;

import com.example.Ecosphere.events.Event;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private ProductPersistence productPersistence;

    ProductController(){
        productPersistence = new ProductPersistence();
    }

    @RequestMapping(value = "/registerProduct", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public boolean createUser(@RequestBody Product product){
        product.setProductName(product.getProductName());
        product.setProductDescription(product.getProductDescription());
        product.setProductLabel(product.getProductLabel());
        product.setProductPrice(product.getProductPrice());
        product.setProductLink(product.getProductLink());
        return product.createProduct(productPersistence);
    }

    @RequestMapping(value = "/loadProducts", produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public List<Product> getProducts(){
        System.out.println("sdsds");
        List<Product> products = productPersistence.loadProducts();
        return products;
    }

    @RequestMapping(value = "/loadProductID", produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public Product loadProductByID(@RequestBody Product product){
        Product products = productPersistence.loadProductByID(product.getProductID());
        return products;
    }

        @RequestMapping(value = "/deleteProduct", produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public boolean deleteProduct(@RequestBody Product product){
        return productPersistence.deleteProduct(product.getProductID());
    }


}
