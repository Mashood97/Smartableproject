package com.example.okcomputers.smartableproject.model;

public class MyOrderClass {

    //in this we get my orders the user placed and session id of it.
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
