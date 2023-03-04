package com.kuwo.entity;

public class Lyric {
    private String lineLyric;//歌词内容
    private String time;//歌词对应的时间

    public Lyric() {
    }

    public Lyric(String lineLyric, String time) {
        this.lineLyric = lineLyric;
        this.time = time;
    }

    public String getLineLyric() {
        return lineLyric;
    }

    public void setLineLyric(String lineLyric) {
        this.lineLyric = lineLyric;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
