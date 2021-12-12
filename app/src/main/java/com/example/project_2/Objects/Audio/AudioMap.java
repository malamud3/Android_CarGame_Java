package com.example.project_2.Objects.Audio;

import com.example.project_2.Objects.Audio.Audio;

import java.util.HashMap;

public class AudioMap {
    private HashMap<String, Audio> sounds;

    public AudioMap(){
        sounds= new HashMap<>();
    }

    public void addAudio(String name, Audio audio){
       this.sounds.put(name,audio);
    }

    public HashMap<String, Audio> getSounds() {
        return sounds;
    }

    public void play(String name) {

        this.sounds.get(name).playAudio();
        if(name.equals("music")) {
            this.sounds.get(name).getMediaPlayer().setLooping(true);
            sounds.get(name).getMediaPlayer().setVolume(20,20);

        }
    }

    public  void pause(String name){
        sounds.get(name).pauseAudio();
    }

}
