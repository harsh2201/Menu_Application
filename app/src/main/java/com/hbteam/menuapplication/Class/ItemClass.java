package com.hbteam.menuapplication.Class;

public class ItemClass {
    public String Name, Price;
    public int Image;

    public ItemClass(int image, String name, String price) {
        Name = name;
        Price = price;
        Image = image;
    }

    public String gName() {
        return Name;
    }

    public void sName(String name) {
        Name = name;
    }

    public String gPrice() {
        return Price;
    }

    public void sPrice(String price) {
        Price = price;
    }

    public int gImage() {
        return Image;
    }

    public void sImage(int image) {
        Image = image;
    }
}
