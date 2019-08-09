package com.mj.communtiy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2019/7/31.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }


}
