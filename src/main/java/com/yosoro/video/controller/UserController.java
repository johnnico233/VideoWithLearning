package com.yosoro.video.controller;

import com.yosoro.video.domain.result.Result;
import com.yosoro.video.domain.result.ResultCode;
import com.yosoro.video.domain.user.LoginUser;
import com.yosoro.video.domain.user.PrivateUser;
import com.yosoro.video.domain.user.User;
import com.yosoro.video.domain.video.Video;
import com.yosoro.video.domain.video.VideoType;
import com.yosoro.video.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Value("${physics-image-dir}")
    private String imageDir;
    @Autowired
    private UserService userService;
    @RequestMapping("/createVideoType")
    public String createVideoTypePage(Model model){
        userService.createNewVideoType(model);
        return "user";
    }
    @RequestMapping(value = "/addTypePicture",method = RequestMethod.POST)
    public @ResponseBody TypeFileName addTypePicture(HttpSession httpSession,@RequestParam MultipartFile file){
        String fileName=((User)httpSession.getAttribute("videoUser")).getId()+new Date().getTime()+
                file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        try(BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(imageDir+"temp/"+fileName))){
            BufferedInputStream inputStream=new BufferedInputStream(file.getInputStream());
            FileCopyUtils.copy(inputStream,outputStream);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return new TypeFileName(fileName);
    }
    @RequestMapping(value = "/addNewVideoType",method = RequestMethod.POST)
    public @ResponseBody Result addNewVideoType(@RequestBody UploadVideoType uploadVideoType){
        System.out.println(uploadVideoType);
        File srcFile=null;
        if(uploadVideoType.getFilePath()!=null)
            srcFile=new File(imageDir+"temp/"+uploadVideoType.getFilePath());
        long typeId=userService.addNewVideoType(uploadVideoType.getTypeName(),uploadVideoType.getDescription(),
                uploadVideoType.getParentId(),
                srcFile==null?null: srcFile.getName().substring(srcFile.getName().lastIndexOf(".")));
        if(srcFile!=null&&typeId!=-1){
            File descFile=new File(imageDir+typeId+srcFile.getName().substring(srcFile.getName().lastIndexOf(".")));
            try {
                FileCopyUtils.copy(srcFile,descFile);
            }catch (IOException ex){
                if(srcFile.exists())
                    srcFile.delete();
                return new Result(ResultCode.ERROR,"添加失败");
            }
        }
        if(srcFile!=null&&srcFile.exists())
            srcFile.delete();
        return new Result(ResultCode.SUCCESS,"ok");
    }
    @RequestMapping(value = "/addParentVideoType",method = RequestMethod.POST)
    public @ResponseBody Result addNewParentVideoType(@RequestBody VideoType videoType){
        long typeId=userService.addNewVideoParentType(videoType.getTypeName());
        if(typeId!=-1)
            return new Result(ResultCode.SUCCESS,"ok");
        else
            return new Result(ResultCode.ERROR,"添加失败");
    }
    @RequestMapping(value = "/deleteVideoTypePage")
    public String deleteVideoTypePage(Model model){
        userService.deleteVideoTypePage(model);
        return "user";
    }
    @RequestMapping(value = "/deleteVideoType")
    public @ResponseBody Result deleteVideoType(@RequestBody VideoType videoType){
        boolean result=userService.deleteVideoType(videoType);
        if(result)
            return new Result(ResultCode.SUCCESS,"ok");
        else
            return new Result(ResultCode.ERROR,"服务器异常,原因未知");
    }
    @RequestMapping(value = "/videoManage")
    public String videoManagePage(Model model,@RequestParam(required = false) String idx){
        userService.setVideoManagePage(model,idx);
        return "user";
    }
    @RequestMapping(value = "/deleteVideo",method = RequestMethod.POST)
    public @ResponseBody Result deleteVideo(@RequestBody Video video){
        boolean result=userService.deleteSingleVideoInfo(video.getVideoId());
        if(result)
            return new Result(ResultCode.SUCCESS,"删除成功");
        else
            return new Result(ResultCode.ERROR,"删除失败,原因位置");
    }
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        userService.logout(request,response);
        return "redirect:/";
    }

    static class TypeFileName{
        String name;

        public TypeFileName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    static class UploadVideoType extends VideoType{
        private String filePath;

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public String toString() {
            return "UploadVideoType{" +
                    "filePath='" + filePath + '\'' +
                    ", id=" + id +
                    ", typeName='" + typeName + '\'' +
                    ", image='" + image + '\'' +
                    ", description='" + description + '\'' +
                    ", createTime=" + createTime +
                    ", count=" + count +
                    ", parentId=" + parentId +
                    '}';
        }
    }
}
