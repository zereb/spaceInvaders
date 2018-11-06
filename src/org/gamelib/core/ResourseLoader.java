package org.gamelib.core;


import invaders.Main;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ResourseLoader {

    public static ResourseLoader resourseLoader = new ResourseLoader();

    Map<String, Image> images = new HashMap<>();
    Map<String, Font> fonts = new HashMap<>();
    Map<String, AudioClip> audioClips = new HashMap<>();

    public ResourseLoader(){}

    public Font loadFont(String path, double size){
        if (fonts.containsKey(path))
            return fonts.get(path);
        else {
            File file = new File("res/fonts/" + path);
            if (file.exists()){
                Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/"+path), size);
                fonts.put(path, font);
                System.out.println(font);
                return font;
            }
        }
        return null;
    }


    public AudioClip loadSound(String path){
        if (audioClips.containsKey(path))
            return audioClips.get(path);
        else {
            File file = new File("res/sfx/"+path);
            if (file.exists()){
                AudioClip audioClip = new AudioClip("file:res/sfx/"+path);
                return audioClip;
            }else{
                AudioClip audioClip = new AudioClip("file:res/sfx/_fail_.wav");
                return audioClip;
            }
        }
    }

    public Image loadImage(String path, int scale){
        if(images.containsKey(path))
            return images.get(path);
        else {
                File file = new File("res/gfx/" + path);
                if (file.exists()) {
                    Image image = new Image("gfx/"+path);
                    if(scale > 1)
                        image = new Image(path, image.getWidth() * scale, image.getHeight() * scale, true, false);
                    images.put(path, image);
                    return image;
                }else{
                    Image image = new Image("gfx/_fail_.png");
                    images.put(path, image);
                    return image;
                }
        }
    }
}
