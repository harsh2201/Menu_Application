package com.hbteam.menuapplication.Adapter;

/**
 * Created by Sanket Patel on 02-06-2019.
 */

public class cart_adapter {
    private String title;
    private Integer price;

    public cart_adapter() {
    }

    public cart_adapter(String title, Integer price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
