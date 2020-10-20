package com.example.buco.model;

import java.util.Date;

public class Receipt {
    public long value;
    public String shop;
    public String category;
    public Date date;
    public String comment;

    public Receipt(long value, String shop, String category, Date date, String comment) {
        this.value = value;
        this.shop = shop;
        this.category = category;
        this.date = date;
        this.comment = comment;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
