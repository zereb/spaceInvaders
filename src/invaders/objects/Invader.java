package invaders.objects;

import invaders.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import org.gamelib.core.Handler;
import org.gamelib.core.ResourseLoader;
import org.gamelib.core.objects.GameObject;
import org.gamelib.core.objects.AnimatedSprite;

import java.awt.*;
import java.util.Random;

public class Invader extends GameObject {

    private int xSpawn;
    private final double FRAME_TIME = 0.5;
    private final double FIRE_COOLDOWN = 5;
    private final int VEL_X = 5;
    private double frameCooldown = 0;
    private double fireCooldown = FIRE_COOLDOWN;
    private Random random;
    private AnimatedSprite sprite;
    private AudioClip sound;

    public Invader(int x, int y, int scale, int type,int id, Handler handler) {
        super(x, y, id, handler);
        xSpawn = x;
        random = new Random();
        sprite = new AnimatedSprite("invaders.png", 12, 8, type,scale);
        sound = ResourseLoader.resourseLoader.loadSound(type+"invader.wav");
        width = sprite.getWidth();
        height = sprite.getHeight();
        velX = VEL_X;
        velY = height / 2;
    }

    @Override
    public Rectangle getBounce() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void update(double timeStep, double time) {
        frameCooldown -= timeStep;
        fireCooldown -= timeStep;
        if (frameCooldown < 0){
//            sound.play();
            x += velX;
            if (x > xSpawn + width * 1.5) {
                velX = -VEL_X;
                y += velY;
            }else if (x < xSpawn){
                velX = VEL_X;
                y += velY;
            }
            frameCooldown = FRAME_TIME;
        }

        if (fireCooldown < 0){
            if (random.nextInt(1000) == 65) {
                handler.addObject(new Bullet(x + width / 2, y, 10, Main.E_BULET, handler));
                fireCooldown = FIRE_COOLDOWN;
            }
        }

        for (GameObject object : handler.getGameObjects()){
            if (object.getId() == Main.BULLET){
                if (getBounce().intersects(object.getBounce())){
                    handler.removeObject(this);
                    handler.removeObject(object);
                    handler.addEvent(Main.ENEMY_DEATH);
                    handler.addObject(new Explosion(x, y, 0.25, Main.NPO, handler));
                }
            }
        }

        sprite.iterate(timeStep, 0.5);
    }

    @Override
    public void render(GraphicsContext gc) {
//        gc.setFill(Color.RED);
//        gc.fillRect(x, y, width, height);
        gc.drawImage(sprite.render(), x, y);
    }
}
