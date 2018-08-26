package com.yosoro.video.domain.video;

import java.util.List;

public class TypeList {
    private VideoType videoType;
    private List<VideoType> subTypes;

    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
    }

    public List<VideoType> getSubTypes() {
        return subTypes;
    }

    public void setSubTypes(List<VideoType> subTypes) {
        this.subTypes = subTypes;
    }

    @Override
    public String toString() {
        return "TypeList{" +
                "videoType=" + videoType +
                ", subTypes=" + subTypes +
                '}';
    }
}
