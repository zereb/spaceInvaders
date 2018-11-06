package org.gamelib.core.objects;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import org.gamelib.core.ResourseLoader;

import java.util.ArrayList;

public class Sprite{


    private Image tileSheet;
    private Image sprite;
    private int width, height;
    private int scale;

    public Sprite(String url, int offsetX, int offsetY, int scale){
       tileSheet = ResourseLoader.resourseLoader.loadImage(url, scale);
       width = offsetX * scale;
       height = offsetY * scale;
       sprite = new WritableImage(tileSheet.getPixelReader(), 0, 0, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight(){
        return height;
    }

    public  Image render(){
        return sprite;
    }


}