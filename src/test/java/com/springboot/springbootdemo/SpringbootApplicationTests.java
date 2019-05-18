package com.springboot.springbootdemo;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests
{
    @Test
    public void callDiscountTest()
    {
        try
        {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            //Defining the post request
            HttpPost postRequest = new HttpPost("http://localhost:8080/SpringBootDemo/returnDiscountResult");

            //Set the API media type in http content-type header
            postRequest.addHeader("content-type", "application/json");

            //Set the request post body
            JSONObject userObject               = new JSONObject();
            JSONArray billPaymentArray          = new JSONArray();
            JSONObject itemType                 = new JSONObject();
            JSONObject relationType             = new JSONObject();
            JSONObject billPaymentProperties    = new JSONObject();

            billPaymentProperties.put("itemPrice",  "990");
            itemType.put("itemTypeId", "1");
            billPaymentProperties.put("itemType", itemType);
            billPaymentArray.put(billPaymentProperties);

            billPaymentProperties = new JSONObject();

            billPaymentProperties.put("itemPrice",  "610");
            itemType.put("itemTypeId", "2");
            billPaymentProperties.put("itemType", itemType);
            billPaymentArray.put(billPaymentProperties);
            userObject.put("billPaymentList", billPaymentArray);
            userObject.put("id", "1");
            userObject.put("accountBeginDate", "24/11/1990");
            relationType.put("relationTypeId", "1");
            userObject.put("relationType", relationType);

            StringEntity userEntity = new StringEntity(userObject.toString());
            postRequest.setEntity(userEntity);

            //Send the request; It will return the response in HttpResponse object
            HttpResponse response = httpClient.execute(postRequest);
            String responseStr = EntityUtils.toString(response.getEntity());

            System.out.println("responseStr" + responseStr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}