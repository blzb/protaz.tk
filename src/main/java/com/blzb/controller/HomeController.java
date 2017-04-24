package com.blzb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by apimentel on 4/24/17.
 */
@Controller
public class HomeController {
    @RequestMapping("")
    public String home(){
        return "home";
    }
}
