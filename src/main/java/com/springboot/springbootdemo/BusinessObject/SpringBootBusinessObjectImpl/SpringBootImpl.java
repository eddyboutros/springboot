package com.springboot.springbootdemo.BusinessObject.SpringBootBusinessObjectImpl;

import com.springboot.springbootdemo.BusinessObject.SpringBootBO;
import com.springboot.springbootdemo.DataModel.BillPayment;
import com.springboot.springbootdemo.DataModel.FinalResponse;
import com.springboot.springbootdemo.DataModel.ItemType;
import com.springboot.springbootdemo.DataModel.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class SpringBootImpl implements SpringBootBO
{
    public FinalResponse getBillAnalysis(User user)
    {
        String itemPrice;
        ItemType itemType;
        String itemTypeId;
        Date parsedBeginDate;
        BillPayment billPayment;
        FinalResponse finalResponse;

        long difference         = 0;
        Double billTotalAmount  = 0.0;
        Double groceryAmount    = 0.0;
        String accountBeginDate = user.getAccountBeginDate();
        List billPaymentList    = user.getBillPaymentList();
        String userRelationType = user.getRelationType() != null ? user.getRelationType().getRelationTypeId() : null;

        parsedBeginDate = this.validateDate(accountBeginDate);

        if(parsedBeginDate != null)
        {
            Date systemDate = new Date();
            difference = (systemDate.getTime() - parsedBeginDate.getTime())/1000/60/60/24/30/12;
        }

        if(billPaymentList != null)
        {
            int listSize = billPaymentList.size();

            for(int i = 0; i < listSize; i++)
            {
                billPayment     = (BillPayment) billPaymentList.get(i);
                itemType        = billPayment.getItemType();
                itemPrice       = billPayment.getItemPrice();

                if(itemPrice != null && !"".equals(itemPrice))
                {
                    billTotalAmount = billTotalAmount + Double.parseDouble(itemPrice);

                    if(itemType != null)
                    {
                        itemTypeId = itemType.getItemTypeId();

                        if("1".equals(itemTypeId))
                        {
                            groceryAmount = groceryAmount + Double.parseDouble(itemPrice);
                        }
                    }
                }
            }
        }

        finalResponse           = new FinalResponse();
        HashMap discountMap     = this.calculateAmounts(billTotalAmount, groceryAmount, difference, userRelationType);
        String newTotalAmount   = discountMap.get("totalNetAmount") + "";

        finalResponse.setErrorCode(Double.parseDouble(newTotalAmount) != billTotalAmount ? "100" : "101");
        finalResponse.setTotalNetAmount(newTotalAmount);
        finalResponse.setErrorDescription(Double.parseDouble(newTotalAmount) != billTotalAmount ?
                "The following discounts were applied: " + discountMap.get("discountType") : "No discounts were applied.");

        return finalResponse;
    }

    private HashMap calculateAmounts(Double totalAmount, Double groceryAmount, long years, String userRelationType)
    {
        String discountType         = "";
        HashMap response            = new HashMap();
        Double amountToDiscount     = 0.0;
        Double nonGroceryAmount     = totalAmount - groceryAmount;

        System.out.println("totalAmount: " + totalAmount);
        System.out.println("amountToDiscount: " + amountToDiscount);

        if ("2".equals(userRelationType))
        {
            nonGroceryAmount    = nonGroceryAmount - nonGroceryAmount * 30 / 100;
            discountType        = discountType + ",30% discount on non-groceries as an employee";
        }
        else if ("1".equals(userRelationType))
        {
            nonGroceryAmount    = nonGroceryAmount - nonGroceryAmount * 10 / 100;
            discountType        = discountType + ",10% discount on non-groceries as an affiliate";
        }
        else if (years > 2)
        {
            nonGroceryAmount    = nonGroceryAmount - nonGroceryAmount * 5 / 100;
            discountType        = discountType + ",5% discount on non-groceries for your over 2 years loyalty";
        }

        if(totalAmount >= 100)
        {
            amountToDiscount    = Math.floor((nonGroceryAmount + groceryAmount)/100) * 5;
            discountType        = discountType + ",5$ discount per 100$";
        }

        totalAmount = nonGroceryAmount + groceryAmount - amountToDiscount;

        response.put("totalNetAmount", totalAmount);
        response.put("discountType", discountType.replaceFirst(",", "") + ".");

        return response;
    }

    private Date validateDate(String dateStr)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try
        {
            Date parsedDate = dateFormat.parse(dateStr);
            return parsedDate;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}