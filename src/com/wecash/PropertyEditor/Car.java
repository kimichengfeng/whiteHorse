package com.wecash.PropertyEditor;

/**
 * Created by chengtong on 2017/7/15.
 */
public class Car {
    private int maxSpeed;
    public String brand;
    private double price;
    //省略get/setter

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
