package com.example.jks.databse;

/**
 * Created by jks on 16.09.14.
 */
public class FoodData {

    private String name;
    private int quantity;
    private String shopping_flag;
    private String added_time;

    public FoodData(String name, int quantity, String shopping_flag, String added_time) {
        this.name = name;
        this.quantity = quantity;
        this.shopping_flag = shopping_flag;
        this.added_time = added_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShopping_flag() {
        return shopping_flag;
    }

    public void setShopping_flag(String shopping_flag) {
        this.shopping_flag = shopping_flag;
    }

    public String getAdded_time() {
        return added_time;
    }

    public void setAdded_time(String added_time) {
        this.added_time = added_time;
    }

    @Override
    public String toString() {
        return (getName() +"-"+ getQuantity()+"-"+ getShopping_flag()+"-"+getAdded_time());
    }
}
