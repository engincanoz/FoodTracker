package com.example.finand;
import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;
import java.util.ArrayList;

public class Product implements Parcelable {
    private String name;
    private Date expirationDate;

    private Date purchaseDate;
    private ArrayList<String> ingredients;
    private static int productID = 0;
    private static final int userID = 1;

    public Product(String name, Date expirationDate, Date purchaseDate,
                   ArrayList<String> ingredients) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.ingredients = ingredients;

        this.purchaseDate = purchaseDate;
        productID++;
    }

    protected Product(Parcel in) {
        name = in.readString();
        expirationDate = (Date) in.readSerializable();

        purchaseDate = (Date) in.readSerializable();
        ingredients = in.createStringArrayList();
        productID = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeSerializable(expirationDate);
        dest.writeSerializable(purchaseDate);
        dest.writeStringList(ingredients);
        dest.writeInt(productID);
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }


    public static int getProductID() {
        return productID;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public static int getUserid() {
        return userID;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void setProductID(int productID) {
        Product.productID = productID;
    }
}
