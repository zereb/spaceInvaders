package org.gamelib.core.objects;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import org.gamelib.core.ResourseLoader;

import java.awt.*;
import java.util.ArrayList;

public class AnimatedSprite {


    private Image tileSheet;
    private ArrayList<WritableImage> frames = new ArrayList<>();
    private int iterator;
    private int maxFrames;
    private int width, height;
    private int scale;
    private double frameCooldown;

    public AnimatedSprite(String url, int offsetX, int offsetY, int row, int scale) {
       tileSheet = ResourseLoader.resourseLoader.loadImage(url, scale);
       width = offsetX * scale;
       height = offsetY * scale;
       iterator = 0;
       maxFrames = (int) (tileSheet.getWidth() / width);
       for(int i = 0; i < maxFrames; i++){
           // :TODO cache writable image
           frames.add(new WritableImage(tileSheet.getPixelReader(), width * i, height * row, width, height));
       }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setScale(int scale){
        this.scale = scale;
    }

    public void iterate(double timeStep, double frameCooldown){
        this.frameCooldown += timeStep;
        if (this.frameCooldown >= frameCooldown){
            this.frameCooldown = 0;
            if (iterator < maxFrames - 1)
                iterator++;
            else
                iterator = 0;
        }
        //return frames.get(iterator);

    }

    public void setIterator(int iterator){
        this.iterator = iterator;
    }

    public  Image render(){
        return frames.get(iterator);
    }


}
