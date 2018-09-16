package com.example.okcomputers.smartableproject.model;

/**
 * Created by OK Computers on 8/26/2018.
 */

//model class is a custom class to set and get data that we want.
public class RecieptClass
{
    //we get itemname,quantity, totalprice and uid .
    private String ItemName;
    private int Quantity;
    private int TotalPrice;
    private String Uid;

    public RecieptClass(){}
    public RecieptClass(String itemName, int quantity, int totalPrice) {
        ItemName = itemName;
        Quantity = quantity;
        TotalPrice = totalPrice;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }


}
