package com.yosoro.video.service;

import com.yosoro.video.dao.VideoMapper;
import com.yosoro.video.domain.video.TypeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class HomeService {
    @Autowired
    private VideoMapper videoMapper;
    public void getHomeVideoTypes(Model model, int index){
        List<TypeList> typeLists=videoMapper.getAllVideoTypeListByRange();
        System.out.println(typeLists);
        model.addAttribute("typeLists",typeLists);
    }
}
