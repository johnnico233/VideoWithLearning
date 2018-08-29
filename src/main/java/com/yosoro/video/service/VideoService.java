package com.yosoro.video.service;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.AddCategoryResponse;
import com.aliyuncs.vod.model.v20170321.UpdateCategoryResponse;
import com.yosoro.video.dao.VideoMapper;
import com.yosoro.video.domain.video.Video;
import com.yosoro.video.oss.OssInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class VideoService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private VideoMapper videoMapper;
    public boolean addNewVideo(Video video){
        boolean result= videoMapper.addNewVideo(video)>0;
        return result;
    }
    public boolean getVideoById(Model model, String id){
        String url;
        if((url=redisTemplate.opsForValue().get(id))==null){
            System.out.println("no cache!");
            url=OssInstance.getVideoUrl(id);
            redisTemplate.opsForValue().append(id,url);
        }
        if(url!=null){
            Video video=videoMapper.getVideoById(id);
            System.out.println(video);
            model.addAttribute("videoUrl",url);
            model.addAttribute("video",video);
            if(video.getVideoType()!=null){
                List<Video> list=videoMapper.getReferVideoByTypeId(video.getVideoType().getId());
                System.out.println(list);
                model.addAttribute("referList",list);
            }
            return true;
        }
        return false;
    }
    public void uploadVideoPageSetting(Model model){
        model.addAttribute("typeList",videoMapper.getAllVideoTypeList());
    }
    public boolean updateVideoInfo(Video video){
        try{
            OssInstance.updateVideoInfo(video);
            return videoMapper.updateVideoInfo(video)>0;
        }catch (ClientException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateVideoType(String name,long id){
        try{
            OssInstance.updateCategoryResponse(id,name);
            return videoMapper.updateVideoType(name,id)>0;
        }catch (ClientException ex){
            ex.printStackTrace();
            return false;
        }
    }
}
