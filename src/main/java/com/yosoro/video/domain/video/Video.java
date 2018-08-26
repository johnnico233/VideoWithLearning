package com.yosoro.video.domain.video;

import java.util.Date;

public class Video {
    private String videoId;
    private String name;
    private String describe;
    private int viewCount;
    private Date uploadTime;
    private VideoType videoType;


    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }



    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId='" + videoId + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", viewCount=" + viewCount +
                ", uploadTime=" + uploadTime +
                ", videoType=" + videoType +
                '}';
    }
}
