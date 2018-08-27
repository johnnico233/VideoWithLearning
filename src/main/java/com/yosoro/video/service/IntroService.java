package com.yosoro.video.service;

import com.yosoro.video.dao.VideoMapper;
import com.yosoro.video.domain.video.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class IntroService {
    @Autowired
    private VideoMapper videoMapper;

    public boolean getIntroUrl(Model model, String index){
        try{
            long idx=Long.valueOf(index);
            List<Video> videoList=videoMapper.getVideoListByTypeId(idx);
            model.addAttribute("videoList",videoList);
            model.addAttribute("videoType",videoMapper.getVideoTypeById(idx));
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
}
