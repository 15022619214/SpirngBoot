package com.spb.boot.Controller;

import com.alibaba.fastjson.JSONObject;
import com.spb.boot.Model.Testinfo;
import com.spb.boot.Service.TestinfoService;
import com.spb.boot.Service.UserService;
import com.spb.boot.Utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private TestinfoService testinfoService;

    @ResponseBody
    @RequestMapping(value = "/getTestinfo" , method = RequestMethod.GET)
    public JSONObject getTestinfo(){
        JSONObject json = new JSONObject();
       List<Testinfo> testinfos = this.testinfoService.findAll();
        json.put("testinfos",testinfos);
        return json;
    }



}
