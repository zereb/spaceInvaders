package invaders;

import invaders.objects.Ufo;
import invaders.objects.util.HudMesage;
import invaders.objects.util.InvaderRow;
import invaders.objects.Player;
import javafx.scene.paint.Color;
import org.gamelib.core.Handler;
import org.gamelib.core.Logic;
import org.gamelib.core.objects.GameObject;
import org.gamelib.core.utils.Hud;
import org.gamelib.core.utils.Save;

public class GameLogic extends Logic{


    private int score;
    private HudMesage hudScore, hudHighScore;
    private int lvl;
    private int highScore;


    public GameLogic(Handler handler, Save save) {
        super(handler);
        handler.clear();
        Hud.objects.clear();
        handler.addObject(new Player(Main.WIDTH / 2, Main.HEIGHT - 48, Main.PLAYER, handler));
        spawnInvaders();
        if (!save.getSaveValue("test").isEmpty()) {
            highScore = Integer.parseInt(save.getSaveValue("test"));
        }
        hudScore = new HudMesage(8, 16, Color.WHITE);
        hudScore.setMessage("Score  " + score * 100);
        hudHighScore = new HudMesage(Main.WIDTH/2 - 34, 16, Color.WHITE);
        hudHighScore.setMessage("High Score " + highScore * 100);
        Hud.objects.add(hudScore);
        Hud.objects.add(hudHighScore);
        lvl = 1;
    }


    private void spawnInvaders(){
        handler.addObject(new Ufo(Main.WIDTH, 38, Main.SCALE, Main.INVADER, handler));
        for (int i = 0; i < 3; i++)
            new InvaderRow( 16, 68 + i * 48 , 1, 8, 2 - i, handler);
            new InvaderRow( 16, 68 + 3 * 48 , 1, 8, 0, handler);
    }

    @Override
    public void update(double timeStep, double time) {
        if (handler.containsEvent(Main.ENEMY_DEATH)){
            score += lvl;
            hudScore.setMessage("Score: " + score * 100);
            int invaders = 0;
            for (GameObject object : handler.getGameObjects())
                if (object.getId() == Main.INVADER)
                    invaders++;
                if (invaders <= 2)
                    spawnInvaders();
        }
        if (handler.containsEvent(Main.UFO_DEATH)){
            score += 10 * lvl;
            hudScore.setMessage("Score: " + score * 100);
        }


    }

    public int getScore(){
        return score;
    }
}
