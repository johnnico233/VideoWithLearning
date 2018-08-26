package com.yosoro.video.domain.video;

public class VideoType {
    private long id;
    private String typeName;
    private String image;

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

    @Override
    public String toString() {
        return "VideoType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
