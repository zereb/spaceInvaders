package org.gamelib.core.utils;

import javafx.scene.canvas.GraphicsContext;
import org.gamelib.core.Handler;
import org.gamelib.core.objects.GameObject;
import org.gamelib.core.utils.HudObjects.HudObject;

import java.util.LinkedList;

public class Hud {


    public static LinkedList<HudObject> objects = new LinkedList<>();

    public void update(double timeStep, double time){
        for (HudObject object : objects)
            object.update(timeStep, time);
    }

    public void render(GraphicsContext gc){
        for (HudObject object : objects)
            object.render(gc);
    }

}
