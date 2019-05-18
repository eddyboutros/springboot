package com.springboot.springbootdemo.DataModel;

public class FinalResponse
{
    private String totalNetAmount;
    private String errorCode;
    private String errorDescription;

    public String getTotalNetAmount()
    {
        return totalNetAmount;
    }

    public void setTotalNetAmount(String totalNetAmount)
    {
        this.totalNetAmount = totalNetAmount;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorDescription()
    {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription)
    {
        this.errorDescription = errorDescription;
    }
}