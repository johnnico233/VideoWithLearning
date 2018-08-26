package com.yosoro.video.controller;

import com.yosoro.video.oss.OssInstance;
import com.yosoro.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String getVideoUrl(Model model, @PathVariable String id){
        videoService.getVideoById(model,id);
        return "video";
    }
}
