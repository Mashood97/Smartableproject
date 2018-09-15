package com.example.okcomputers.smartableproject.model;

/**
 * Created by OK Computers on 8/26/2018.
 */

public class EstimateTimeClass {

    private String ItemName;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public EstimateTimeClass(){}
    public EstimateTimeClass(String name) {
        this.ItemName = name;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }
}
