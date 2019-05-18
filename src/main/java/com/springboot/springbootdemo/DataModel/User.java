package com.springboot.springbootdemo.DataModel;

import java.util.List;

public class User
{
    private String id;
    private String name;
    private String accountBeginDate;
    private RelationType relationType;
    private List<BillPayment> billPaymentList;

    public RelationType getRelationType()
    {
        return relationType;
    }

    public void setRelationType(RelationType relationType)
    {
        this.relationType = relationType;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAccountBeginDate()
    {
        return accountBeginDate;
    }

    public void setAccountBeginDate(String accountBeginDate)
    {
        this.accountBeginDate = accountBeginDate;
    }

    public List<BillPayment> getBillPaymentList()
    {
        return billPaymentList;
    }

    public void setBillPaymentList(List<BillPayment> billPaymentList)
    {
        this.billPaymentList = billPaymentList;
    }
}