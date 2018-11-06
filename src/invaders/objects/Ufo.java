package invaders.objects;

import invaders.Main;
import javafx.scene.canvas.GraphicsContext;
import org.gamelib.core.Handler;
import org.gamelib.core.objects.GameObject;
import org.gamelib.core.objects.AnimatedSprite;

import java.awt.*;

public class Ufo extends GameObject {

    private AnimatedSprite sprite;

    public Ufo(int x, int y, int scale, int id, Handler handler) {
        super(x, y, id, handler);
        velX = -2;
        sprite = new AnimatedSprite("ufo.png", 16, 8, 0,scale);
        sprite.setScale(2);
        width = sprite.getWidth();
        height = sprite.getHeight();

    }

    @Override
    public Rectangle getBounce() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void update(double timeStep, double time) {
        x +=velX;
        if (x < -1500)
            x = Main.WIDTH;
        sprite.iterate(timeStep, 0.5);

        for (GameObject object : handler.getGameObjects()){
            if (object.getId() == Main.BULLET){
                if (getBounce().intersects(object.getBounce())){
                    handler.removeObject(this);
                    handler.removeObject(object);
                    handler.addEvent(Main.UFO_DEATH);
                    handler.addObject(new Explosion(x, y, 0.5, Main.NPO, handler));
                }
            }
        }

    }

    @Override
    public void render(GraphicsContext gc)
    {
        gc.drawImage(sprite.render(), x, y);
    }
}
