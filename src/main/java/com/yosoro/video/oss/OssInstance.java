package com.yosoro.video.oss;

import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import com.yosoro.video.domain.video.Video;
import java.util.List;

public class OssInstance {
    public final static String accessKey="LTAICFPRKjvaP5IL";
    public final static String secret="LtA7juhLBSM5bbDIYtXeEplTu167Rd";
    private final static DefaultProfile profile=DefaultProfile.getProfile("cn-shenzhen",accessKey,secret);
    private volatile static DefaultAcsClient client=null;
    public static DefaultAcsClient getDefaultAcsClient(){
        if(client==null){
            synchronized(DefaultAcsClient.class){
                if (client==null)
                    client=new DefaultAcsClient(profile);
            }
        }
        return client;
    }
    public static CreateUploadVideoResponse getUploadVideoResponse(Video video,String fileName){
        CreateUploadVideoRequest request=new CreateUploadVideoRequest();
        //设置要上传视频的信息
        request.setTitle(video.getName());
        request.setDescription(video.getDescribe());
        request.setFileName(fileName);
        request.setCateId(video.getVideoType().getId());
        CreateUploadVideoResponse response=null;
        try{
            response=getDefaultAcsClient().getAcsResponse(request);
        }catch (ClientException ex){
            ex.printStackTrace();
        }
        return response;
    }

    public static String getVideoUrl(String videoId){
        DefaultAcsClient client=getDefaultAcsClient();
        GetPlayInfoRequest request=new GetPlayInfoRequest();
        request.setVideoId(videoId);
        try{
            GetPlayInfoResponse response=client.getAcsResponse(request);
            List<GetPlayInfoResponse.PlayInfo> list=response.getPlayInfoList();
            for(GetPlayInfoResponse.PlayInfo playInfo:list){
                if(playInfo.getFormat().equals("mp4"))
                    return playInfo.getPlayURL();
            }
        }catch (ClientException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static UpdateVideoInfoResponse updateVideoInfo(Video video)throws ClientException{
        UpdateVideoInfoRequest request=new UpdateVideoInfoRequest();
        request.setVideoId(video.getVideoId());
        request.setTitle(video.getName());
        request.setDescription(video.getDescribe());
        request.setCateId(video.getVideoType().getId());
        return getDefaultAcsClient().getAcsResponse(request);
    }
    //可以删除多个video或者单个video,每个video 用 ',' 分割
    public static DeleteVideoResponse deleteVideoInfo(String videoId)throws ClientException{
        DeleteVideoRequest request=new DeleteVideoRequest();
        request.setVideoIds(videoId);
        return getDefaultAcsClient().getAcsResponse(request);
    }
    public static AddCategoryResponse addCategoryResponse(String name,long parentId)throws ClientException{
        AddCategoryRequest request=new AddCategoryRequest();
        request.setParentId(parentId);
        request.setCateName(name);
        return getDefaultAcsClient().getAcsResponse(request);
    }
    public static UpdateCategoryResponse updateCategoryResponse(long id,String name)throws ClientException{
        UpdateCategoryRequest request=new UpdateCategoryRequest();
        request.setCateId(id);
        request.setCateName(name);
        return getDefaultAcsClient().getAcsResponse(request);
    }
    public static DeleteCategoryResponse deleteCategoryResponse(long id)throws ClientException{
        DeleteCategoryRequest request=new DeleteCategoryRequest();
        request.setCateId(id);
        return getDefaultAcsClient().getAcsResponse(request);
    }
}
