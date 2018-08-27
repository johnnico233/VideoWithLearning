package com.yosoro.video.dao;

import com.yosoro.video.domain.video.TypeList;
import com.yosoro.video.domain.video.Video;
import com.yosoro.video.domain.video.VideoType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VideoMapper {
    int addNewVideo(Video video);
    Video getVideoById(String id);
    List<TypeList> getAllVideoTypeList();
    int updateVideoInfo(Video video);
    int deleteVideoInfo(String videoId);
    int addNewVideoType(Map<String,Object> map);
    int updateVideoType(@Param("name")String name,@Param("id") long id);
    void deleteVideoType(Map<String,Object> map);
    List<TypeList> getAllVideoTypeListByRange(int start);
    List<Video> getVideoListByTypeId(long videoId);
    VideoType getVideoTypeById(long typeId);
}
