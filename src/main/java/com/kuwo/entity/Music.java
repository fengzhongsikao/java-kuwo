package com.kuwo.entity;

public class Music {
    private String artist; //歌手名称
    private String pic;//图片地址
    private String rid;//歌曲的rid
    private String releaseDate;//歌曲发布时间
    private String album;//专辑名称
    private String songTimeMinutes;//播放时长
    private String name ;//歌曲名称
    private Integer hasmv; //是否有Mv

    public Music() {
    }

    public Music(String artist, String pic, String rid, String releaseDate, String album, String songTimeMinutes, String name, Integer hasmv) {
        this.artist = artist;
        this.pic = pic;
        this.rid = rid;
        this.releaseDate = releaseDate;
        this.album = album;
        this.songTimeMinutes = songTimeMinutes;
        this.name = name;
        this.hasmv = hasmv;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getSongTimeMinutes() {
        return songTimeMinutes;
    }

    public void setSongTimeMinutes(String songTimeMinutes) {
        this.songTimeMinutes = songTimeMinutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHasmv() {
        return hasmv;
    }

    public void setHasmv(Integer hasmv) {
        this.hasmv = hasmv;
    }
}
