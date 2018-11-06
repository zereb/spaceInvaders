package invaders.objects;

import invaders.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.gamelib.core.Handler;
import org.gamelib.core.ResourseLoader;
import org.gamelib.core.objects.GameObject;

import java.awt.*;

public class Bullet extends GameObject {

    public Bullet(int x, int y,int velY, int id, Handler handler) {
        super(x, y, id, handler);
        width = 4;
        height = 8;
        this.velY = velY;
        ResourseLoader.resourseLoader.loadSound("shoot.wav").play();
    }

    @Override
    public Rectangle getBounce() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void update(double timeStep, double time) {
        y += velY;
        if (y < 0 || y > Main.HEIGHT)
            handler.removeObject(this);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillRect(x, y, width, height);
    }
}
