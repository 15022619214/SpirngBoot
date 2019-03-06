package com.spb.boot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ViewsController {

    /*首页*/
    @RequestMapping("/")
    public String home_(Model model){
        return "Home";
    }

    /*登录页*/
    @RequestMapping("/login")
    public String login(Model model){
        return "Home";
    }

    /*test*/
    @RequestMapping("/system")
    public String system(Model model){
        return "System";
    }
}
