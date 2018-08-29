package com.yosoro.video.domain.video;

import java.util.Date;

public class VideoType {
    protected long id;
    protected String typeName;
    protected String image;
    protected String description;
    protected Date createTime;
    protected int count;
    protected long parentId;

    public VideoType(long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public VideoType() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "VideoType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", count=" + count +
                ", parentId=" + parentId +
                '}';
    }
}
