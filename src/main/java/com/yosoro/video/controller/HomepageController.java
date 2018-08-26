package com.yosoro.video.controller;

import com.yosoro.video.oss.OssInstance;
import com.yosoro.video.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomepageController {
    @Autowired
    private HomeService homeService;
    @RequestMapping(method = RequestMethod.GET)
    public String homepage(Model model){
        homeService.getHomeVideoTypes(model,1);
        return "home";
    }
}
