package com.example.foodtracker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ProductRepository {
    private static Timer expirationTimer;
    private static final long DELAY_MILLIS = 0; // Initial delay before the first execution
    private static final long PERIOD_MILLIS = 3600000; // 1 hour in milliseconds

    public ProductRepository() {
        expirationTimer = new Timer();
        expirationTimer.scheduleAtFixedRate(new ExpirationCheckTask(), DELAY_MILLIS, PERIOD_MILLIS);
    }
    private class ExpirationCheckTask extends TimerTask {
        @Override
        public void run() {
            // Perform expiration check and update ArrayList here
            retrieveExpiredProducts();
        }
    }

    public void createProductTable() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db")) {
            // Create Product table
            String createProductTableQuery = "CREATE TABLE IF NOT EXISTS Products (" +
                    "Product_ID INTEGER PRIMARY KEY," +
                    "User_ID INTEGER REFERENCES User(User_ID)," +
                    "Name TEXT NOT NULL," +
                    "Expiration_Date TEXT NOT NULL," +
                    "Purchase_Date TEXT NOT NULL," +
                    "Ingredients TEXT NOT NULL" +
                    ")";
            try (PreparedStatement productTableStatement = connection.prepareStatement(createProductTableQuery)) {
                productTableStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Product insertOrUpdateProductData(Product product) {
        String ingredientsString = "";
        for (int i = 0; i < product.getIngredients().size() - 1; i++) {
            ingredientsString += product.getIngredients().get(i) + ", ";
        }
        ingredientsString += product.getIngredients().get(product.getIngredients().size() - 1);
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db")) {
            String insertOrUpdateQuery = "INSERT OR REPLACE INTO Products (Product_ID, User_ID, Name, Expiration_Date, Purchase_Date, Ingredients) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertOrUpdateQuery)) {
                statement.setObject(1, Product.getProductID());
                statement.setObject(2, 1);
                statement.setString(3, product.getName());
                statement.setDate(4, product.getExpirationDate());
                statement.setDate(5, product.getPurchaseDate());
                statement.setString(6, ingredientsString);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public ArrayList<Product> retrieveExpiredProducts() {
        ArrayList<Product> expiredList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:your_database.db")) {
            String query = "SELECT products.Product_ID, products.User_ID, products.Name AS ProductName, " +
                    "products.Expiration_Date, products.Ingredients, products.Purchase_Date, " +
                    "products.Photo, users.Name AS UserName, users.Surname, "+
                    "users.Allergens, users.Unwanted_Ingredients " +
                    "FROM products " +
                    "LEFT JOIN users ON products.User_ID = users.User_ID " +
                    "WHERE products.Expiration_Date < date('now')";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Product product = mapResultSetToProduct(resultSet);
                    expiredList.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expiredList;
    }

    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("ProductName");
        Date expirationDate = resultSet.getDate("Expiration_Date");

        Date purchaseDate = resultSet.getDate("Purchase_Date");

        String ingredientsString = resultSet.getString("Ingredients");
        String[] ingredientsArray = ingredientsString.split(", ");
        ArrayList<String> ingredientsList = new ArrayList<>();
        for (String ingredient : ingredientsArray) {
            ingredientsList.add(ingredient);
        }

        Product product = new Product(name, expirationDate, purchaseDate, ingredientsList);

        return product;
    }

    /*public ArrayList<ResultSet> retrieveShoppingListProducts() {
        ArrayList<ResultSet> shoppigtList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:your_database.db")) {
            String query = "SELECT products.Product_ID, products.User_ID, products.Name AS ProductName, " +
                    "products.Expiration_Date, products.Ingredients, products.Purchase_Date, " +
                    "products.Photo, users.Name AS UserName, users.Surname, users.Age, " +
                    "users.Weight, users.Height, users.Gender, users.Daily_Activity_Level, " +
                    "users.Allergens, users.Unwanted_Ingredients " +
                    "FROM products " +
                    "LEFT JOIN users ON products.User_ID = users.User_ID " +
                    "WHERE (julianday(products.Expiration_Date) - julianday(products.Purchase_Date)) * 100 / 80 < (julianday('now') - julianday(products.Purchase_Date))";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    shoppigtList.add(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shoppigtList;
    }*/

    public ArrayList<Product> retrieveAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:your_database.db")) {
            String query = "SELECT * FROM products";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Product product = mapResultSetToProduct(resultSet);
                    productList.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public void removeProduct(int productId) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db")) {
            String deleteQuery = "DELETE FROM Products WHERE Product_ID = ?";
            try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                statement.setInt(1, productId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
