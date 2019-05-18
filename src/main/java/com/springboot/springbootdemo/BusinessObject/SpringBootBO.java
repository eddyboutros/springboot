package com.springboot.springbootdemo.BusinessObject;

import com.springboot.springbootdemo.DataModel.FinalResponse;
import com.springboot.springbootdemo.DataModel.User;
import org.springframework.stereotype.Component;

@Component
public interface SpringBootBO
{
    FinalResponse getBillAnalysis(User user) throws Exception;
}