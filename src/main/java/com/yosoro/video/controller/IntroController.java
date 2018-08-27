package com.yosoro.video.controller;

import com.yosoro.video.service.IntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IntroController {
    @Autowired
    private IntroService introService;
    @RequestMapping(value = "/intro/{id}",method = RequestMethod.GET)
    public String getIntroUrl(@PathVariable String id, Model model){
        introService.getIntroUrl(model,id);
        return "intro";
    }
}
