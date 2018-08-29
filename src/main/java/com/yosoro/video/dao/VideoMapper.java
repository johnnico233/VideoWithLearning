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
    void addNewVideoType(Map<String,Object> map);
    int updateVideoType(@Param("name")String name,@Param("id") long id);
    void deleteVideoType(Map<String,Object> map);
    List<TypeList> getAllVideoTypeListByRange();
    List<Video> getVideoListByTypeId(long videoId);
    VideoType getVideoTypeById(long typeId);
    List<VideoType> getVideoOnlyParentType();
    List<String> getSubTypeImageName(long id);
    int subtractParentTypeCount(long id);
    String getTypeImageName(long id);
    List<Video> getVideoByRange(@Param("start") int start,@Param("size") int size);
    int getVideoSize();
    List<Video> getReferVideoByTypeId(long id);
}
