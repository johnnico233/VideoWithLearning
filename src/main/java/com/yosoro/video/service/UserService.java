package com.yosoro.video.service;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.AddCategoryResponse;
import com.yosoro.video.dao.UserMapper;
import com.yosoro.video.dao.VideoMapper;
import com.yosoro.video.domain.timer.EmailTimer;
import com.yosoro.video.domain.user.LoginUser;
import com.yosoro.video.domain.user.PrivateUser;
import com.yosoro.video.domain.user.User;
import com.yosoro.video.domain.video.TypeList;
import com.yosoro.video.domain.video.Video;
import com.yosoro.video.domain.video.VideoType;
import com.yosoro.video.oss.OssInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

@Service
public class UserService {
    @Value("${physics-image-dir}")
    private String imagePath;
    @Autowired
    private ExecutorService executorService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ConcurrentHashMap<String,EmailTimer> emailTimerMap;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    public boolean UserEmailExist(String email, HttpSession session){
        EmailTimer emailTimer;
        if((emailTimer=emailTimerMap.get(email))!=null){
            if(emailTimer.getSessionId().equals(session.getId())){
                emailTimer.setStartTime(System.currentTimeMillis());
                emailTimerMap.put(email,emailTimer);
                return true;
            }
            return false;
        }
        else{
            //参数life代表的是该email最多存活时间,以秒作为单位
            if(userMapper.checkUserExist(email)==0)
                return emailTimerMap.put(email,
                        new EmailTimer(email,System.currentTimeMillis(),30,session.getId()))==null;
            return false;
        }
    }

    public boolean addNewUser(User user){
        HashMap<String,Object> map=new HashMap<>();
        map.put("mail",user.getMail());
        map.put("password",user.getPassword());
        map.put("userName",user.getUserName());
        map.put("result",0);
        userMapper.addNewUser(map);
        return (int)map.get("result")>0;
    }

    public boolean checkPasswordValid(LoginUser user, HttpServletResponse response,HttpSession session){
        User privateUser=userMapper.checkLoginValid(user);
        session.setAttribute("videoUser",privateUser);
        if(privateUser!=null&&user.isRemember()){
            Cookie cookie=new Cookie("videoUser",String.valueOf(privateUser.getId()));
            cookie.setPath("/");
            cookie.setMaxAge(3600*24*7);
            response.addCookie(cookie);
        }
        return privateUser!=null;
    }

    public void createNewVideoType(Model model){
        model.addAttribute("pageType","addVideoType");
        model.addAttribute("parentTypeList",videoMapper.getVideoOnlyParentType());
    }

    /*
     * create new video type with it's father,if the parentId = -1 ,it means creating the new father video type
     * @return the video type id ,-1 means add operation false
     * */
    public long addNewVideoType(String name,String description,long parentId,String fileType){
        try{
            AddCategoryResponse response= OssInstance.addCategoryResponse(name,parentId);
            long typeId=response.getCategory().getCateId();
            Map<String,Object> map=new HashMap<>();
            map.put("typeId",typeId);
            map.put("typeName",name);
            map.put("fatherId",parentId==-1?null:parentId);
            map.put("result",-1);
            map.put("description",description);
            map.put("image",fileType!=null?(typeId+fileType):"default.jpg");
            videoMapper.addNewVideoType(map);
            return (int)map.get("result")>0?typeId:-1;
        }catch (ClientException ex){
            ex.printStackTrace();
            return -1;
        }
    }
    //create the new video father type
    public long addNewVideoParentType(String name){
        return addNewVideoType(name,null,-1,null);
    }

    public void deleteVideoTypePage(Model model){
        List<TypeList> list=videoMapper.getAllVideoTypeList();
        model.addAttribute("typeLists",list);
        model.addAttribute("pageType","deleteVideoType");
    }

    public boolean deleteVideoType(VideoType videoType){
        try{
            OssInstance.deleteCategoryResponse(videoType.getId());
            Map<String,Object> map=new HashMap<>();
            map.put("videoId",videoType.getId());
            map.put("result",-1);
            if(videoType.getParentId()==-1){
                videoMapper.deleteVideoType(map);
                List<String> imageList=videoMapper.getSubTypeImageName(videoType.getId());
                executorService.execute(()->{
                    for(String name:imageList){
                        File file=new File(imagePath+name);
                        if(file.exists())
                            file.delete();
                    }
                });
            }
            else{
                String fileName=videoMapper.getTypeImageName(videoType.getId());
                videoMapper.deleteVideoType(map);
                videoMapper.subtractParentTypeCount(videoType.getParentId());
                executorService.execute(()->{
                    if(fileName!=null){
                        File file=new File(imagePath+fileName);
                        if(file.exists())
                            file.delete();
                    }
                });
            }
            return (int)map.get("result")>0;
        }catch (ClientException ex){
            System.out.println(ex.getLocalizedMessage());
            return false;
        }

    }

    public void setVideoManagePage(Model model,String idx){
        int index=1;
        if(idx!=null){
            try{
                index=Integer.valueOf(idx);
            }catch (NumberFormatException ex){
                ex.printStackTrace();
            }
        }
        List<Video> list=videoMapper.getVideoByRange((index-1)*10,10);
        for(Video video:list)
            System.out.println(video);
        model.addAttribute("videoList",list);
        model.addAttribute("index",index);
        model.addAttribute("videoSize",Integer.valueOf((videoMapper.getVideoSize()-1)/10)+1);
        model.addAttribute("pageType","videoManage");
    }
    public boolean deleteSingleVideoInfo(String id){
        try{
            OssInstance.deleteVideoInfo(id);
            redisTemplate.delete(id);
            return videoMapper.deleteVideoInfo(id)>0;
        }catch (ClientException ex){
            ex.printStackTrace();
            return false;
        }
    }
    public void logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().invalidate();
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("videoUser")){
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                System.out.println("delete cookies");
                break;
            }
        }
    }
}
