package com.springboot.springbootdemo.controller;

import com.springboot.springbootdemo.BusinessObject.SpringBootBO;
import com.springboot.springbootdemo.DataModel.FinalResponse;
import com.springboot.springbootdemo.DataModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/SpringBootDemo")
public class SpringBootController
{
    @Autowired
    SpringBootBO bootBO;

    /**
     * @param user
     * @return FinalResponse
     * */
    @ResponseBody
    @RequestMapping(value = "/returnDiscountResult", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FinalResponse ProjectController(@RequestBody User user)
    {
        FinalResponse response = null;

        try
        {
            response = bootBO.getBillAnalysis(user);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return response;
    }
}