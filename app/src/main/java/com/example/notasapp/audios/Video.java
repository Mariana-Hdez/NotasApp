package com.example.notasapp.audios;

import android.net.Uri;

class Video {
    private final Uri uri;
    private final String name;
    private final int duration;
    private final int size;

    @Override
    public String toString(){
        return "Audio{" +
                "uri=" + uri +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", size=" + size + '}';
    }

    public Video(Uri uri, String name, int duration, int size) {
        this.uri = uri;
        this.name = name;
        this.duration = duration;
        this.size = size;
    }
}
