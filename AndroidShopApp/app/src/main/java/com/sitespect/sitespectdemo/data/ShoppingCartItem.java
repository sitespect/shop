package com.sitespect.sitespectdemo.data;

import android.support.annotation.DrawableRes;

public class ShoppingCartItem {
    private String itemName;
    private float unitPrice;
    private int itemQuantity;
    private int itemDrawableId;

    public ShoppingCartItem(String itemName, float unitPrice, int itemQuantity, @DrawableRes int itemDrawableId) {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.itemDrawableId = itemDrawableId;
        this.itemQuantity = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void incrementQuantity() {
        this.itemQuantity++;
    }

    public void setItemQuantity(int quantity) {
        this.itemQuantity = quantity;
    }

    public @DrawableRes int getItemDrawableId() {
        return itemDrawableId;
    }
}
