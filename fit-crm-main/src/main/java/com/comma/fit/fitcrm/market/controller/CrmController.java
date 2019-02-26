package com.comma.fit.fitcrm.market.controller;

import com.comma.fit.fitcrm.market.service.IAbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CrmController {
    @Autowired
    IAbcService iAbcService ;

    @GetMapping("/hello")
    @ResponseBody
    public String index(){
        return iAbcService.sayHello();
    }

    @RequestMapping("/index")
    public String index2(){
        return "index";
    }
}
