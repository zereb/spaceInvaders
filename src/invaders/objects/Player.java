package invaders.objects;

import invaders.Main;
import invaders.objects.Bullet;
import invaders.objects.util.HudHealth;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import org.gamelib.core.Handler;
import org.gamelib.core.ResourseLoader;
import org.gamelib.core.objects.AnimatedSprite;
import org.gamelib.core.objects.GameObject;
import org.gamelib.core.utils.Hud;
import org.gamelib.core.utils.Utils;

import java.awt.*;

public class Player extends GameObject {

    public static double FIRE_COOLDOWN = 1.2;
    public double fireCooldown = FIRE_COOLDOWN;
    public int lives = 3;
    public HudHealth hudHealth;
    private AnimatedSprite sprite;
    private AudioClip explosionSound;
    private double gotHitCooldown = 0;

    public Player(int x, int y, int id, Handler handler) {
        super(x, y, id, handler);
        sprite = new AnimatedSprite("player.png", 11, 8, 0, Main.SCALE);
        explosionSound = ResourseLoader.resourseLoader.loadSound("explosion.wav");
        hudHealth = new HudHealth(Main.WIDTH - 68,8);
        hudHealth.setLives(lives);
        Hud.objects.add(hudHealth);
        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    @Override
    public Rectangle getBounce() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void update(double timeStep, double time) {
        if (gotHitCooldown > 0){
            gotHitCooldown -= timeStep;
            sprite.iterate(timeStep, 0.125);
            velX = 0;
        }else
            sprite.setIterator(0);
        x = Utils.clamp(x + velX, 0, Main.WIDTH - width);
        fireCooldown -=timeStep;
        if (event && fireCooldown < 0) {
            handler.addObject(new Bullet(x + width / 2, y, -10, Main.BULLET, handler));
            fireCooldown = 2;
        }

        event = false;

        if (lives <= 0){
            handler.addEvent(Main.DEATH_EVENT);
        }

        for (GameObject object : handler.getGameObjects()){
            if (object.getId() == Main.E_BULET){
                if (getBounce().intersects(object.getBounce())){
                    handler.removeObject(object);
                    lives--;
                    hudHealth.setLives(lives);
                    explosionSound.play();
                    gotHitCooldown = 0.5;
                }
            }
        }


    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(sprite.render(), x, y);
    }
}
