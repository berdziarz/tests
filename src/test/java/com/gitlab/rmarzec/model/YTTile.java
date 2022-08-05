package com.gitlab.rmarzec.model;

public class YTTile {
    private static final String LIVE = "live";

    String title;
    String channel;
    String length;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length != null ? length : LIVE;
    }

    public boolean isNotLive() {
        return !length.equals(LIVE);
    }
}
