package invaders.objects.util;

import invaders.objects.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.gamelib.core.objects.Sprite;
import org.gamelib.core.utils.HudObjects.HudObject;

public class HudHealth extends HudObject {

    public int lives = 0;
    private int size = 8;
    private Sprite heart;

    public HudHealth(int x, int y){
        super(x, y);
        heart = new Sprite("heart.png", size, size, 2);
    }

    @Override
    public void update(double timeStep, double time) {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        for (int i = 0; i < lives; i++)
            gc.drawImage(heart.render(),x + (i * (size * 2 + 4)), y);
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
