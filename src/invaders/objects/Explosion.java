package invaders.objects;

import invaders.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import org.gamelib.core.Handler;
import org.gamelib.core.ResourseLoader;
import org.gamelib.core.objects.GameObject;
import org.gamelib.core.objects.AnimatedSprite;


import java.awt.*;

public class Explosion extends GameObject {

    private double duration;
    private AnimatedSprite sprite;

    public Explosion(int x, int y, double duration,int id, Handler handler) {
        super(x, y, id, handler);
        this.duration = duration;
        sprite = new AnimatedSprite("explosion.png", 13, 7, 0, Main.SCALE);
        ResourseLoader.resourseLoader.loadSound("invaderkilled.wav").play();
    }

    @Override
    public Rectangle getBounce() {
        return null;
    }

    @Override
    public void update(double timeStep, double time) {
        duration -=  timeStep;
        if (duration <= 0)
            handler.removeObject(this);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(sprite.render(), x, y);
    }
}
