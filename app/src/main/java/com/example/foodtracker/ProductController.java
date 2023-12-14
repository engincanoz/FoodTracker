package com.example.foodtracker;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController {

    ProductRepository productRepository = new ProductRepository();

    public void enterName(Product product, String name) {

        product.setName(name);

        productRepository.insertOrUpdateProductData(product);
    }

    public void enterExpirationDate(Product product, Date date) {

        product.setExpirationDate(date);

        productRepository.insertOrUpdateProductData(product);
    }

    public void addImage(Product product, String photo) {

        product.setPhoto(photo);

        productRepository.insertOrUpdateProductData(product);
    }

    public void addIngredient(Product product, String newIngredient) {

        ArrayList<String> ingredientsList = product.getIngredients();

        ingredientsList.add(newIngredient);

        product.setIngredients(ingredientsList);

        productRepository.insertOrUpdateProductData(product);
    }

    public void addProduct(String name, Date expirationDate, String photo, Date purchaseDate,
                           ArrayList<String> ingredients) {

        Product newProduct = new Product(name, expirationDate, photo, purchaseDate, ingredients);

        productRepository.insertOrUpdateProductData(newProduct);
    }

    public ArrayList<Product> getExpiredProducts() {
        return productRepository.retrieveExpiredProducts();
    }

    public ArrayList<com.example..Product> removeProductById(int productID) {
        ArrayList<com.example..Product> productList = productRepository.retrieveAllProducts();
        ArrayList<com.example..Product> updatedList = new ArrayList<>();

        for (com.example..Product product : productList) {
            if (product.getProductID() != productID) {
                updatedList.add(product);
            }
        }
        productRepository.removeProduct(productID);
        return updatedList;
    }

    public String determineFreshness(com.example..Product prd) {
        long allTime = prd.getExpirationDate().getTime() - prd.getPurchaseDate().getTime();
        long passedTime = System.currentTimeMillis() - prd.getPurchaseDate().getTime();

        if (passedTime <= allTime * 0.2) {
            return "Fresh";
        }
        else if (allTime * 0.2 < passedTime && passedTime <= allTime * 0.8){
            return "Good";
        }
        return "Expiring";
    }




}
