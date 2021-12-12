package com.example.project_2.Objects.Audio;

import android.media.MediaPlayer;

public class Audio {
private MediaPlayer mediaPlayer;

public Audio(MediaPlayer mediaPlayer){this.mediaPlayer=mediaPlayer;};



    public void playAudio() {
        mediaPlayer.start();
    }

    public void pauseAudio(){
        mediaPlayer.pause();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }



}
