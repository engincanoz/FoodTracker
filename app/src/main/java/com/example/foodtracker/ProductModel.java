package com.example.foodtracker;
public class ProductModel {
    String productName;
    String freshness;
    String expirationdate;

    public ProductModel(String productName, String freshness, String expirationdate) {
        this.productName = productName;
        this.freshness = freshness;
        this.expirationdate = expirationdate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFreshness() {
        return freshness;
    }

    public void setFreshness(String freshness) {
        this.freshness = freshness;
    }

    public String getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(String expirationdate) {
        this.expirationdate = expirationdate;
    }
}
