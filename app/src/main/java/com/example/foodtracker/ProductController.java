package com.example.foodtracker;

import android.content.Context;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController {
    private ProductRepository productRepository;

    public ProductController(Context context) {
        this.productRepository = new ProductRepository(context);
    }

    public void enterName(Product product, String name) {

        product.setName(name);

        productRepository.insertOrUpdateProductData(product);
    }

    public void enterExpirationDate(Product product, Date date) {

        product.setExpirationDate(date);

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

        Product newProduct = new Product(name, expirationDate, purchaseDate, ingredients);

        productRepository.insertOrUpdateProductData(newProduct);
    }

    public ArrayList<Product> getExpiredProducts() {
        return productRepository.retrieveExpiredProducts();
    }

    public ArrayList<Product> removeProductById(int productID) {
        ArrayList<Product> productList = productRepository.retrieveAllProducts();
        ArrayList<Product> updatedList = new ArrayList<>();

        for (Product product : productList) {
            if (product.getProductID() != productID) {
                updatedList.add(product);
            }
        }
        productRepository.removeProduct(productID);
        return updatedList;
    }

    public String determineFreshness(Product prd) {
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

/*    public void addImage(Product product, String photo) {

        product.setPhoto(photo);

        productRepository.insertOrUpdateProductData(product);
    }*/


}
