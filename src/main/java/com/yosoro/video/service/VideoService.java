package com.yosoro.video.service;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.AddCategoryResponse;
import com.aliyuncs.vod.model.v20170321.UpdateCategoryResponse;
import com.yosoro.video.dao.VideoMapper;
import com.yosoro.video.domain.video.Video;
import com.yosoro.video.oss.OssInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;


@Service
public class VideoService {
    @Autowired
    private VideoMapper videoMapper;
    public boolean addNewVideo(Video video){
        return videoMapper.addNewVideo(video)>0;
    }
    public boolean getVideoById(Model model, String id){
        String url=OssInstance.getVideoUrl(id);
        if(url!=null){
            Video video=videoMapper.getVideoById(id);
            System.out.println(video);
            model.addAttribute("videoUrl",url);
            model.addAttribute("video",video);
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
    public boolean deleteSingleVideoInfo(String id){
        try{
            OssInstance.deleteVideoInfo(id);
            return videoMapper.deleteVideoInfo(id)>0;
        }catch (ClientException ex){
            ex.printStackTrace();
            return false;
        }
    }
    //create new video type with it's father,if the parentId = -1 ,it means creating the new father video type
    public boolean addNewVideoType(String name,long parentId){
        try{
            AddCategoryResponse response=OssInstance.addCategoryResponse(name,parentId);
            long typeId=response.getCategory().getCateId();
            Map<String,Object> map=new HashMap<>();
            map.put("typeId",typeId);
            map.put("typeName",name);
            map.put("fatherId",parentId==-1?null:parentId);
            return videoMapper.addNewVideoType(map)>0;
        }catch (ClientException ex){
            ex.printStackTrace();
            return false;
        }
    }
    //create the new video father type
    public boolean addNewVideoParentType(String name){
        return addNewVideoType(name,-1);
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
    public boolean deleteVideoType(long id){
        try{
            OssInstance.deleteCategoryResponse(id);
            Map<String,Object> map=new HashMap<>();
            map.put("videoId",id);
            map.put("result",-1);
            videoMapper.deleteVideoType(map);
            return (int)map.get("result")>0;
        }catch (ClientException ex){
            System.out.println(ex.getLocalizedMessage());
            return false;
        }

    }
}
