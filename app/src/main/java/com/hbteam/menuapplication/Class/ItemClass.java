package com.hbteam.menuapplication.Class;

public class ItemClass {
    public String Name, Price,Description;
    public String Image;

    public ItemClass(String image, String name, String price,String description) {
        Name = name;
        Price = price;
        Image = image;
        Description=description;
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

    public String gImage() {
        return Image;
    }

    public void sImage(String image) {
        Image = image;
    }

    public String gDesc(){ return Description; }

    public void sDesc(String desc) { Description = desc; }
}
