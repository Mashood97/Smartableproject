package com.example.okcomputers.smartableproject.model;

public class MyOrderClass {

    private int mMyOrder;
    public  String SessionId = "";

    public MyOrderClass()
    {}
    public MyOrderClass(int MyOrder)
    {
     this.mMyOrder = MyOrder;
    }

    public int getOrder()
    {
        return mMyOrder;
    }
    public void setOrder(int orderNo)
    {
        this.mMyOrder = orderNo;
    }
}
