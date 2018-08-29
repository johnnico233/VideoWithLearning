package com.yosoro.video.controller;

import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.yosoro.video.domain.video.Video;
import com.yosoro.video.domain.result.Result;
import com.yosoro.video.domain.result.ResultCode;
import com.yosoro.video.domain.video.VideoType;
import com.yosoro.video.oss.OssInstance;
import com.yosoro.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class UploadVideoController {
    @Autowired
    private VideoService videoService;
    @RequestMapping(method = RequestMethod.GET)
    public String getUploadUrl(Model model){
        videoService.uploadVideoPageSetting(model);
        return "uploadVideo";
    }
    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public @ResponseBody
    CreateUploadVideoResponse uploadFile(@RequestParam String title, @RequestParam String describe,
                                         @RequestParam String fileName,@RequestParam String type){
        Video video=new Video();
        video.setName(title);
        video.setDescribe(describe);
        VideoType videoType=new VideoType();
        videoType.setId(Long.parseLong(type));
        video.setVideoType(videoType);
        System.out.println(video);
        return OssInstance.getUploadVideoResponse(video,fileName);
    }
    @RequestMapping(value="/write",method = RequestMethod.POST)
    public @ResponseBody Result insertVideoToDatabase(@RequestBody Video video){
        boolean result=videoService.addNewVideo(video);
        String text=result?"上传成功":"写入数据库失败,请联系数据库管理员";
        return new Result(result?ResultCode.SUCCESS:ResultCode.ERROR,text);
    }
}
