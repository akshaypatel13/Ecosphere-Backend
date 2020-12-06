package com.example.Ecosphere.Product;

import com.example.Ecosphere.databaseConfiguration.StoredProcedure;
import com.example.Ecosphere.events.Event;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductPersistence {

    public boolean createProduct(Product product) {
        StoredProcedure proc = null;
        try {
            proc = new StoredProcedure("spCreateProduct(?, ?, ?, ?,?)");
            proc.setParameter(1,product.getProductName());
            proc.setParameter(2, product.getProductDescription());
            proc.setParameter(3, product.getProductLabel());
            proc.setParameter(4, product.getProductPrice());
            proc.setParameter(5,product.getProductLink());
            proc.statementExecute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanUp();
            }
        }
        return true;
    }

    public List<Product> loadProducts() {
        StoredProcedure proc = null;
        ArrayList<Product> products = new ArrayList<Product>() ;
        try {

            proc = new StoredProcedure("spLoadProducts()");
            ResultSet results = proc.resultSetExecution();
            if (null != results) {
                while (results.next()) {
                    Product product = new Product();
                    product.setProductID(results.getLong(1));
                    product.setProductName(results.getString(2));
                    product.setProductDescription(results.getString(3));
                    product.setProductLabel(results.getString(4));
                    product.setProductPrice(results.getString(5));
                    product.setProductLink(results.getString(6));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanUp();
            }
        }
        return products;
    }

    public Product loadProductByID(long productID) {
        StoredProcedure proc = null;
        Product product = new Product();

        try {
            proc = new StoredProcedure("spLoadProductsByID(?)");
            proc.setParameter(1,productID);
            ResultSet results = proc.resultSetExecution();
            if (null != results) {
                while (results.next()) {
                    product.setProductID(results.getLong(1));
                    product.setProductName(results.getString(2));
                    product.setProductDescription(results.getString(3));
                    product.setProductLabel(results.getString(4));
                    product.setProductPrice(results.getString(5));
                    product.setProductLink(results.getString(6));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanUp();
            }
        }
        return product;
    }

    public boolean deleteProduct(long productID) {
        StoredProcedure proc = null;
        try {
            proc = new StoredProcedure("spDeleteProduct(?)");
            proc.setParameter(1,productID);
            proc.statementExecute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != proc) {
                proc.cleanUp();
            }
        }
        return true;
    }
}
