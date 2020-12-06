package com.example.Ecosphere.Product;

public class Product {

    private long productID;
    private String productName;
    private String productDescription;
    private String productLabel;
    private String productPrice;
    private String productLink;

    Product(){
        productID = -1;
        productName = "";
        productDescription = "";
        productLabel = "";
        productPrice = "";
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductLabel() {
        return productLabel;
    }

    public void setProductLabel(String productLabel) {
        this.productLabel = productLabel;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }


    public boolean createProduct(ProductPersistence productPersistence) {
        return productPersistence.createProduct(this);
    }
}
